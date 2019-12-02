package com.cooperativeX.votation.restvote.service;

import com.cooperativeX.votation.restvote.Exception.GenericAgendaException;
import com.cooperativeX.votation.restvote.Exception.SessionGenericExistDaoException;
import com.cooperativeX.votation.restvote.Exception.SessionTimeException;
import com.cooperativeX.votation.restvote.dao.AgendaDao;
import com.cooperativeX.votation.restvote.dao.ResultDao;
import com.cooperativeX.votation.restvote.dao.SessionDao;
import com.cooperativeX.votation.restvote.dao.VoteDao;
import com.cooperativeX.votation.restvote.domain.Agenda;
import com.cooperativeX.votation.restvote.domain.Result;
import com.cooperativeX.votation.restvote.domain.Session;
import com.cooperativeX.votation.restvote.domain.Vote;
import com.cooperativeX.votation.restvote.service.enums.ErrorMessages;
import com.cooperativeX.votation.restvote.service.enums.ResultStatus;
import com.cooperativeX.votation.restvote.service.enums.SessionStatus;
import com.cooperativeX.votation.restvote.service.enums.VoteOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class VotationService {
    private static final  Logger logger = LogManager.getLogger(VotationService.class);
    @Autowired
    AgendaDao agendaDao;
    @Autowired
    VoteDao voteDao;
    @Autowired
    SessionDao sessionDao;
    @Autowired
    ResultDao resultDao;
    Agenda agenda;

    public void CreateAgenda(Agenda agenda)
    {
        logger.debug(agenda.toString());
        agendaDao.save(agenda);
    }

    public void AddVote(Vote vote) {
        logger.debug(vote.toString());
        this.agenda = getAgenda(vote.getAgendaId());
        verifyOpenedSession(this.agenda.getSession().getStartVotation(), this.agenda.getSession().getEndVotation());
        this.agenda.setVote(vote);
        voteDao.save(vote);
        agendaDao.save(this.agenda);
    }

    public void CreateSession(Session session) {
        logger.debug(session.toString());
        this.agenda = getAgenda(session.getAgendaId());
        session = verifySessionDuration(session);
        validateSessionPresence();
        this.agenda.setSession(session);
        sessionDao.save(session);
        agendaDao.save(this.agenda);
    }

    public void OpenSession(Session session) {
        logger.debug(session.toString());
        this.agenda = getAgenda(session.getAgendaId());
        verifyOpenedStatusSession();
        session = verifySessionDuration(session);
        setSessionPeriodAndStatus(session);
        this.agenda.setSession(session);
        sessionDao.save(session);
        agendaDao.save(this.agenda);
    }

    public Result endSession(long agendaId) {
        logger.debug("EndSession " + agendaId);
        this.agenda = getAgenda(agendaId);
        verifyClosedSession(this.agenda.getSession().getEndVotation());
        Result result = CalculateResult(agendaId, new Result());
        resultDao.save(result);
        this.agenda.setResult(result);
        this.agenda.getSession().setSessionStatus(SessionStatus.CLOSED);
        agendaDao.save(this.agenda);
        return this.agenda.getResult();
    }

    public Session setSessionPeriodAndStatus(Session session) {
        logger.debug("setSessionPeriodAndStatus " + session.toString());
        session.setStartVotation(Instant.now().getEpochSecond());
        session.setEndVotation(Instant.now().getEpochSecond() + session.getDurationMinutes() * 60);
        session.setSessionStatus(SessionStatus.OPENED);
        return session;
    }

    public com.cooperativeX.votation.restvote.domain.Session verifySessionDuration(Session session) {
        if (session.getDurationMinutes() == null) {
            session.setDurationMinutes(60);
        }
        return session;
    }

    public Agenda getAgenda(Long agendaId) {
        Optional<Agenda> agendaOptional = agendaDao.findById(agendaId);
        validateAgendaPresence(agendaOptional);
        return agendaOptional.get();
    }

    private void validateAgendaPresence(Optional<Agenda> ObjectOptional) {
        if (!ObjectOptional.isPresent()) {
            logger.error(ErrorMessages.AGENDA_NOT_FOUND.getMessage());
            throw new GenericAgendaException(ErrorMessages.AGENDA_NOT_FOUND.getMessage());
        }
    }

    private void validateSessionPresence() {
        if (this.agenda.getSession() != null)
            logger.error(ErrorMessages.SESSION_SESSION_ALREDY_EXIST.getMessage());
            throw new SessionGenericExistDaoException(ErrorMessages.SESSION_SESSION_ALREDY_EXIST.getMessage());
    }

    private void verifyOpenedStatusSession() {
        if (this.agenda.getSession().getSessionStatus().equals(SessionStatus.OPENED))
            logger.error(ErrorMessages.SESSION_ALREDY_OPEN.getMessage());
            throw new SessionGenericExistDaoException(ErrorMessages.SESSION_ALREDY_OPEN.getMessage());
    }

    public void verifyOpenedSession(Long startSession, Long endSession) {
        if (Instant.now().getEpochSecond() <= startSession) {
            logger.error(ErrorMessages.SESSION_NOT_OPENED.getMessage());
            throw new SessionTimeException(ErrorMessages.SESSION_NOT_OPENED.getMessage());
        }
        if (Instant.now().getEpochSecond() >= endSession) {
            logger.error(ErrorMessages.SESSION_FININSHED.getMessage());
            throw new SessionTimeException(ErrorMessages.SESSION_FININSHED.getMessage());
        }
    }

    public void verifyClosedSession(Long endSession) {
        if (Instant.now().getEpochSecond() <= endSession) {
            logger.error(ErrorMessages.SESSION_NOT_CLODED.getMessage());
            throw new SessionTimeException(ErrorMessages.SESSION_NOT_CLODED.getMessage());
        }
        if (endSession == null) {
            logger.error(ErrorMessages.SESSION_NOT_OPENED.getMessage());
            throw new SessionTimeException(ErrorMessages.SESSION_NOT_OPENED.getMessage());

        }
    }

    public Result CalculateResult(long agendaId, Result result) {
        logger.debug("CalculateResult " +  result.toString());
        result.setVotesTotalYes(voteDao.countAllByAgendaIdAndVoteOptionEquals(agendaId, VoteOptions.YES));
        result.setVotesTotalNo(voteDao.countAllByAgendaIdAndVoteOptionEquals(agendaId, VoteOptions.NO));
        result.setVotesTotal(result.getVotesTotalNo() + result.getVotesTotalYes());
        if (result.getVotesTotalYes() > result.getVotesTotalNo()) {
            result.setResultStatus(ResultStatus.YES);
        } else if (result.getVotesTotalYes() < result.getVotesTotalNo()) {
            result.setResultStatus(ResultStatus.NO);
        } else if (result.getVotesTotalYes() == 0 && result.getVotesTotalNo() == 0) {
            result.setResultStatus(ResultStatus.NO_VOTES_COMPUTED);
        } else if (result.getVotesTotalYes() == result.getVotesTotalNo()) {
            result.setResultStatus(ResultStatus.DRAW);
        }
        return result;
    }

}
