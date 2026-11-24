package com.supmti.sos_application.core.model

/** Local presentation-only example. This model must never imply real-world verification. */
data class DemoResource(
    val name: String,
    val category: String,
    val description: String,
    val locationLabel: String = "Démonstration",
    val verifiedPhoneNumber: String? = null,
    val isOfficiallyVerified: Boolean = false,
) {
    val canCall: Boolean
        get() = isOfficiallyVerified && !verifiedPhoneNumber.isNullOrBlank()
}

val demoResourceExample = DemoResource(
    name = "Exemple de ressource (démonstration)",
    category = "Exemple",
    description = "Contenu fictif destiné à prévisualiser une carte. Aucune ressource réelle n’est référencée.",
)
