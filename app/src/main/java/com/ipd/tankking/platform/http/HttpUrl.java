package com.ipd.tankking.platform.http;

/**
 * Created by jumpbox on 16/5/2.
 */
public interface HttpUrl {
    String BASE_URL = "http://185.186.147.142/";
    String WEB_URL = BASE_URL + "tkwz/";
    String SERVER_URL = BASE_URL + "tkwz/";

    String RECHARGE_VIP = WEB_URL + "1/html/member/member-1.html";
    String GAME_MATCH = WEB_URL + "1/html/match/match.html";
    String MONEY_STORE = WEB_URL + "1/html/gold/gold-1.html";
    String GOLD_STORE = WEB_URL + "1/html/diload/diload.html";
    String LOTTERY = WEB_URL + "1/html/diloadsup/diloadsup.html";
    String RANK = WEB_URL + "y/tk1/militaryExploits.html";
    String TEAM = WEB_URL + "y/tk1/myTeam.html";


}
