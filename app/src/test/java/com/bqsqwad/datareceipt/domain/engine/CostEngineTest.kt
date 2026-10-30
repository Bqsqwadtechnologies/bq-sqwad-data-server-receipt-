package com.bqsqwad.datareceipt.domain.engine

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.math.BigDecimal

class CostEngineTest {

    private val engine = CostEngine()

    @Test
    fun `zero usage returns zero cost`() {
        val cost = engine.calculateCost(0, 1_000_000_000L, BigDecimal("1000"))
        assertThat(cost).isEqualTo(BigDecimal.ZERO)
    }

    @Test
    fun `full bundle usage equals full price`() {
        val price = BigDecimal("1500.00")
        val bytes = 2L * 1024 * 1024 * 1024 // 2 GB
        val cost = engine.calculateCost(bytes, bytes, price)
        assertThat(cost).isEqualTo(BigDecimal("1500.0000"))
    }

    @Test
    fun `half usage is half price`() {
        val price = BigDecimal("1000")
        val total = 1_000_000_000L
        val cost = engine.calculateCost(total / 2, total, price)
        assertThat(cost).isEqualTo(BigDecimal("500.0000"))
    }

    @Test
    fun `bytesFromGb uses 1024 by default`() {
        val bytes = engine.bytesFromGb(1.0, use1024 = true)
        assertThat(bytes).isEqualTo(1024L * 1024 * 1024)
    }
}
