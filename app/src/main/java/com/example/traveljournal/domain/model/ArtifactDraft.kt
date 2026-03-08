package com.example.traveljournal.domain.model

import java.time.Instant

data class ArtifactDraft(
    val id: String,
    val tripId: String,
    val capturedAt: Instant?
) {
    fun validateDateRequired() {
        require(capturedAt != null) { "Artifacts require a capture date." }
    }
}
