package quest.laxla.yyii

import org.junit.jupiter.api.Nested
import org.quiltmc.loader.api.ModInternal
import kotlin.test.*

@ModInternal
internal class KansasServerTest {
    companion object {
        private const val OVERWORLD_BOTTOM_Y = -64
        private const val OVERWORLD_HEIGHT = 384
    }

    @Nested
    inner class Overworld {
        @Test
        fun `y=449, optimization on`() {
            assertEquals(
                expected = 3,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = 449,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=449, optimization off`() {
            assertEquals(
                expected = 2,
                actual = KansasServer.getFloorIndexAt(
                    y = 449,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=448, optimization on`() {
            assertEquals(
                expected = 3,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = 448,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=448, optimization off`() {
            assertEquals(
                expected = 2,
                actual = KansasServer.getFloorIndexAt(
                    y = 448,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=447, optimization on`() {
            assertEquals(
                expected = 2,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = 447,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=447, optimization off`() {
            assertEquals(
                expected = 1,
                actual = KansasServer.getFloorIndexAt(
                    y = 447,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=446, optimization on`() {
            assertEquals(
                expected = 2,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = 446,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=446, optimization off`() {
            assertEquals(
                expected = 1,
                actual = KansasServer.getFloorIndexAt(
                    y = 446,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=385, optimization on`() {
            assertEquals(
                expected = 2,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = 385,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=385, optimization off`() {
            assertEquals(
                expected = 1,
                actual = KansasServer.getFloorIndexAt(
                    y = 385,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=384, optimization on`() {
            assertEquals(
                expected = 2,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = 384,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=384, optimization off`() {
            assertEquals(
                expected = 1,
                actual = KansasServer.getFloorIndexAt(
                    y = 384,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=383, optimization on`() {
            assertEquals(
                expected = 1,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = 383,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=383, optimization off`() {
            assertEquals(
                expected = 1,
                actual = KansasServer.getFloorIndexAt(
                    y = 383,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=382, optimization on`() {
            assertEquals(
                expected = 1,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = 382,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=382, optimization off`() {
            assertEquals(
                expected = 1,
                actual = KansasServer.getFloorIndexAt(
                    y = 382,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=321, optimization on`() {
            assertEquals(
                expected = 1,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = 321,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=321, optimization off`() {
            assertEquals(
                expected = 1,
                actual = KansasServer.getFloorIndexAt(
                    y = 321,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=320, optimization on`() {
            assertEquals(
                expected = 1,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = 320,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=320, optimization off`() {
            assertEquals(
                expected = 1,
                actual = KansasServer.getFloorIndexAt(
                    y = 320,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=319, optimization on`() {
            assertEquals(
                expected = 0,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = 319,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=319, optimization off`() {
            assertEquals(
                expected = 0,
                actual = KansasServer.getFloorIndexAt(
                    y = 319,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=318, optimization on`() {
            assertEquals(
                expected = 0,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = 318,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=318, optimization off`() {
            assertEquals(
                expected = 0,
                actual = KansasServer.getFloorIndexAt(
                    y = 318,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=1, optimization on`() {
            assertEquals(
                expected = 0,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = 1,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=1, optimization off`() {
            assertEquals(
                expected = 0,
                actual = KansasServer.getFloorIndexAt(
                    y = 1,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=0, optimization on`() {
            assertEquals(
                expected = 0,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = 0,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=0, optimization off`() {
            assertEquals(
                expected = 0,
                actual = KansasServer.getFloorIndexAt(
                    y = 0,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=-1, optimization on`() {
            assertEquals(
                expected = 0,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = -1,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=-1, optimization off`() {
            assertEquals(
                expected = 0,
                actual = KansasServer.getFloorIndexAt(
                    y = -1,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=-63, optimization on`() {
            assertEquals(
                expected = 0,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = -63,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=-63, optimization off`() {
            assertEquals(
                expected = 0,
                actual = KansasServer.getFloorIndexAt(
                    y = -63,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=-64, optimization on`() {
            assertEquals(
                expected = 0,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = -64,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=-64, optimization off`() {
            assertEquals(
                expected = 0,
                actual = KansasServer.getFloorIndexAt(
                    y = -64,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=-65, optimization on`() {
            assertEquals(
                expected = -1,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = -65,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=-65, optimization off`() {
            assertEquals(
                expected = -1,
                actual = KansasServer.getFloorIndexAt(
                    y = -65,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=-127, optimization on`() {
            assertEquals(
                expected = -127,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = -127,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=-127, optimization off`() {
            assertEquals(
                expected = -1,
                actual = KansasServer.getFloorIndexAt(
                    y = -127,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=-128, optimization on`() {
            assertEquals(
                expected = -1,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = -128,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=-128, optimization off`() {
            assertEquals(
                expected = -1,
                actual = KansasServer.getFloorIndexAt(
                    y = -128,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=-129, optimization on`() {
            assertEquals(
                expected = -2,
                actual = KansasServer.getFloorIndexAtOptimized(
                    y = -129,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }

        @Test
        fun `y=-129, optimization off`() {
            assertEquals(
                expected = -1,
                actual = KansasServer.getFloorIndexAt(
                    y = -129,
                    bottomY = OVERWORLD_BOTTOM_Y,
                    height = OVERWORLD_HEIGHT
                )
            )
        }
    }

    @Nested
    inner class Nether

    @Nested
    inner class End
}
