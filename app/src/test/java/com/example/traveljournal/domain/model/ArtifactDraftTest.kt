package com.example.traveljournal.domain.model

import org.junit.Assert.assertThrows
import org.junit.Test
import java.time.Instant

class ArtifactDraftTest {
    @Test
    fun validateDateRequired_throwsWhenCapturedAtMissing() {
        val draft = ArtifactDraft(id = "a1", tripId = "t1", capturedAt = null)

        assertThrows(IllegalArgumentException::class.java) {
            draft.validateDateRequired()
        }
    }

    @Test
    fun validateDateRequired_passesWhenCapturedAtPresent() {
        val draft = ArtifactDraft(id = "a1", tripId = "t1", capturedAt = Instant.now())

        draft.validateDateRequired()
    }
}
