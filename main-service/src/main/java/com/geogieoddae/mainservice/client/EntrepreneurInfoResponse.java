package com.geogieoddae.mainservice.client;

import lombok.Data;

@Data
public class EntrepreneurInfoResponse {
    private Long entrepreneurCode;
    private Long entrepreneurId;
    private String entrepreneurCertificateUrl;
    private Integer entrepreneurBankAccount;
    private String entrepreneurStatus;
    private Long userCode;
}
