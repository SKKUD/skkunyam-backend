package com.skkudteam3.skkusirenorder.src.entity;

public enum OrderStatus {

    PRE_WAITING,
    WAITING,
    PROCEEDING,
    COMPLETE,
    CANCEL
/*
PRE-WAITING : 주문 객체 생성 후 결제되기 전 상태 (최초 Order객체의 상태)
WAITING : 현재 주문 접수 대기 중 (UI상 대기중인 주문)
PROCEEDING : 현재 주문 접수 후 진행중 (UI상 진행중인 주문)
COMPLETE  : 현재 주문 완료 (UI상 완료된 주문)
CANCEL : (가게측) 주문 거부됐거나 (사용자측) 주문 취소한 상태
*/
}
