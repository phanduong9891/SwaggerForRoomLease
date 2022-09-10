package com.axonactive.roomLeaseManagement.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
@Slf4j
public class ExceptionList extends Exception {
    public static ResponseException notFound(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.NOT_FOUND);
    }

    public static ResponseException badRequest(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.BAD_REQUEST);
    }

    public static ResponseException internalServerError(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static final String ASSET_NOT_FOUND_MSG_KEY = "AssetNotExisted";
    private static final String ASSET_NOT_FOUND_MSG = "Asset Not Found";

    public static ResponseException assetNotFound() {
        return notFound(ASSET_NOT_FOUND_MSG_KEY, ASSET_NOT_FOUND_MSG);
    }


    private static final String ROOM_NOT_FOUND_MSG_KEY = "RoomNotExisted";
    private static final String ROOM_NOT_FOUND_MSG = "Room Not Found";

    public static ResponseException roomNotFound() {
        return notFound(ROOM_NOT_FOUND_MSG_KEY, ROOM_NOT_FOUND_MSG);
    }

    private static final String OWNER_NOT_FOUND_MSG_KEY = "OwnerNotExisted";
    private static final String OWNER_NOT_FOUND_MSG = "Owner Not Found";

    public static ResponseException ownerNotFound() {
        return notFound(OWNER_NOT_FOUND_MSG_KEY, OWNER_NOT_FOUND_MSG);
    }

    private static final String CONTRACTDEAL_NOT_FOUND_MSG_KEY = "ContractDealNotExisted";
    private static final String CONTRACTDEAL_NOT_FOUND_MSG = "Contract deal Not Found";

    public static ResponseException contractDealNotFound() {
        return notFound(CONTRACTDEAL_NOT_FOUND_MSG_KEY, CONTRACTDEAL_NOT_FOUND_MSG);
    }

    private static final String CONTRACT_NOT_FOUND_MSG_KEY = "ContractNotExisted";
    private static final String CONTRACT_NOT_FOUND_MSG = "Contract Not Found";

    public static ResponseException contractNotFound() {
        return notFound(CONTRACT_NOT_FOUND_MSG_KEY, CONTRACT_NOT_FOUND_MSG);
    }

    private static final String TENANT_NOT_FOUND_MSG_KEY = "TenantNotExisted";
    private static final String TENANT_NOT_FOUND_MSG = "Tenant Not Found";

    public static ResponseException tenantNotFound() {
        return notFound(TENANT_NOT_FOUND_MSG_KEY, TENANT_NOT_FOUND_MSG);
    }

    private static final String RELATIVE_NOT_FOUND_MSG_KEY = "RelativeNotExisted";
    private static final String RELATIVE_NOT_FOUND_MSG = "Relative Not Found";

    public static ResponseException relativeNotFound() {
        return notFound(RELATIVE_NOT_FOUND_MSG_KEY, RELATIVE_NOT_FOUND_MSG);
    }

    private static final String MONTHLYPAYMENT_NOT_FOUND_MSG_KEY = "MonthlyPaymentNotExisted";
    private static final String MONTHLYPAYMENT_NOT_FOUND_MSG = "Monthly payment Not Found";

    public static ResponseException monthlyPaymentNotFound() {
        return notFound(MONTHLYPAYMENT_NOT_FOUND_MSG_KEY, MONTHLYPAYMENT_NOT_FOUND_MSG);
    }


    private static final String MONTHLYSERVICEUSING_NOT_FOUND_MSG_KEY = "MonthlyServiceUsingNotExisted";
    private static final String MONTHLYSERVICEUSING_NOT_FOUND_MSG = "Monthly-service-using Not Found";

    public static ResponseException monthlyServiceUsingNotFound() {
        return notFound(MONTHLYSERVICEUSING_NOT_FOUND_MSG_KEY, MONTHLYSERVICEUSING_NOT_FOUND_MSG);
    }


    private static final String PAYMENTUPONTERMINATION_NOT_FOUND_MSG_KEY = "PaymentUponTerminationNotExisted";
    private static final String PAYMENTUPONTERMINATION_NOT_FOUND_MSG = "Payment-upon-termination Not Found";

    public static ResponseException paymentUponTerminationNotFound() {
        return notFound(PAYMENTUPONTERMINATION_NOT_FOUND_MSG_KEY, PAYMENTUPONTERMINATION_NOT_FOUND_MSG);
    }
}
