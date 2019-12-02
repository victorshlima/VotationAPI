package com.cooperativeX.votation.restvote.service.enums;

public enum ErrorMessages {

    SESSION_NOT_OPENED(101, "Session not Opened"),
    SESSION_NOT_CLODED(102, "Session not closed"),
    SESSION_FININSHED(103, "Session Finished"),
    SESSION_ALREDY_OPEN(104, "Error Session alredy Open"),
    SESSION_SESSION_ALREDY_EXIST(105, "Error Session alredy Open"),
    AGENDA_NOT_FOUND(205, "Error Agenda not found"),
    USER_ALREDY_EXIST(306, "This user already exists.");

    private final int id;
    private final String message;

    ErrorMessages(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() { return id; }
    public String getMessage() { return message; }
}



