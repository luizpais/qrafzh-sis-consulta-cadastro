package org.quarkusclub.dtos;

import java.util.UUID;

public record ClienteStatusResponse(UUID idConveniado, UUID idConvenio, boolean status){
}
