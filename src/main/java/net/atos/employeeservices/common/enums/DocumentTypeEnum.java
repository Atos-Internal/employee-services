package net.atos.employeeservices.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DocumentTypeEnum {
    ATTESTATION_TRAVAIL("Attestation de travail"),
    ATTESTATION_SALAIRE("Attestation de salaire"),
    ATTESTATION_STAGE("Attestation de stage"),
    CERTIF_TRAVAIL("Certificat de travail"),
    DOMICILIATION("Domiciliation"),
    ATTESTATION_CONGE("Attestation de congé"),
    ATTESTATION_SALAIRE_ANNUELLE("Attestation de salaire annuelle"),
    ORDRE_MISSION("Ordre de mission"),
    ATTESTATION_TITULARISATION("Attestation de titularisation"),

    FIN_PERIODE_ESSAI("Fin période d'essaie"),
    RENOUVELLEMENT("Renouvelement"),
    CONFIRMATION("Confirmation"),
    REFUS("Refus"),
    REDUCTION("Réduction"),
    STANDARD("Standard");

    private final String label;
}