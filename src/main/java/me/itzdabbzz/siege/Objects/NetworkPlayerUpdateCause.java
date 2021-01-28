package me.itzdabbzz.siege.Objects;

public enum NetworkPlayerUpdateCause {

    NOTSET(),
    LOGIN(),
    LOGOUT(),
    BAN(),
    KICK(),
    UNBAN(),
    WARN(),
    REPORTTAKE(),
    HISTORYUPDATE(),
    STAFFSETTINGS(),
    REPORTSEND(),
    REPORTDENY(),
    REPORTDELETE(),
    REPORTPROCESS(),
    PLAYERSTATS(),
    PROPERTIES(),
    GAMEPLAYED(),
    GAMELOST(),
    GAMEWON(),
    KDUPDATE();
}
