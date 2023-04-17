package com.chaeeunm.webservice.security;

import java.util.Locale;

public enum CommonOauth2Provider {
    GOOGLE{
        @Override
        public Locale.Builder getBuilder(String registrationId){
            ClientRegistration.Builder builder = getBuilder(registrationId,ClientAuthenticationMethod.BASIC, )
        }
    }
}
