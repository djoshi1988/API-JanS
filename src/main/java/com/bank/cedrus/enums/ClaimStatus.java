package com.bank.cedrus.enums;

public enum ClaimStatus {
   IN_PROGRESS(11),
   QUERIED(7),
   REJECTED(8),
   APPROVED(10);

   private final int value;

   ClaimStatus(int value) {
       this.value = value;
   }

   public int getValue() {
       return value;
   }
    
}
