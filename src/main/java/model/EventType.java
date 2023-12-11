package model;

import framework.IEventType;

public enum EventType implements IEventType {
    ARR_RECEP,
    ARR_NURSE,
    ARR_DOCTOR,
    ARR_XRAY,
    ARR_MRI,
    ARR_BLOOD,
    ARR_EKG,

    DEP_RECEP,
    DEP_NURSE1,
    DEP_NURSE2,
    DEP_NURSE3,
    DEP_NURSE4,
    DEP_DOCTOR1,
    DEP_DOCTOR2,
    DEP_DOCTOR3,
    DEP_XRAY,
    DEP_MRI,
    DEP_BLOOD,
    DEP_EKG
}
