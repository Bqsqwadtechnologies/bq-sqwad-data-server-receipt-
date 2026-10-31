package com.bqsqwad.datareceipt.domain.engine

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class CostEngineTest {

    private val engine = CostEngine()

    @Test
    fun `zero usage returns zero cost`() {
        val cost = engine.calculateCost(0L, 1_073_741_824L, BigDecimal("1000"))
        assertEquals(BigDecimal("0.00"), cost)
    }

    @Test
    fun `half bundle returns half price`() {
        val oneGb = 1_073_741_824L
        val cost = engine.calculateCost(oneGb / 2, oneGb, BigDecimal("1000"))
        assertEquals(0, cost.compareTo(BigDecimal("500.00")))
    }

    @Test
    fun `full bundle returns full price`() {
        val oneGb = 1_073_741_824L
        val cost = engine.calculateCost(oneGb, oneGb, BigDecimal("500"))
        assertEquals(0, cost.compareTo(BigDecimal("500.00")))
    }
}
