package com.bqsqwad.datareceipt.domain.engine

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal
import java.math.RoundingMode

class HotspotSplitTest {

    @Test
    fun `split evenly between two people`() {
        val total = BigDecimal("100.00")
        val people = 2
        val share = total.divide(BigDecimal(people), 2, RoundingMode.HALF_UP)
        assertEquals(BigDecimal("50.00"), share)
    }

    @Test
    fun `split with odd amount`() {
        val total = BigDecimal("100.00")
        val people = 3
        val share = total.divide(BigDecimal(people), 2, RoundingMode.HALF_UP)
        assertEquals(BigDecimal("33.33"), share)
    }

    @Test
    fun `one person gets full amount`() {
        val total = BigDecimal("75.50")
        val share = total.divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP)
        assertEquals(BigDecimal("75.50"), share)
    }
}
