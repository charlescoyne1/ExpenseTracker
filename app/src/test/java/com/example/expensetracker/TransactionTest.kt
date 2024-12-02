import com.example.expensetracker.Transaction
import org.junit.Assert.assertEquals
import org.junit.Test

class TransactionTest {

    @Test
    fun `isIncome returns true for positive amount`() {
        val transaction = Transaction(id = 1, label = "Salary", amount = 500.0, description = "Income")
        assertEquals(true, transaction.isIncome())
    }

    @Test
    fun `isIncome returns false for negative amount`() {
        val transaction = Transaction(id = 2, label = "Rent", amount = -200.0, description = "Expense")
        assertEquals(false, transaction.isIncome())
    }

    @Test
    fun `formattedAmount returns correct string`() {
        val transaction1 = Transaction(id = 1, label = "Salary", amount = 500.0, description = "Income")
        val transaction2 = Transaction(id = 2, label = "Rent", amount = -200.0, description = "Expense")

        assertEquals("+ $500.00", transaction1.formattedAmount())
        assertEquals("- $200.00", transaction2.formattedAmount())
    }

    @Test
    fun `getColorResource returns correct color`() {
        val green = 1  // Mock value for R.color.green
        val red = 2    // Mock value for R.color.red

        val transaction1 = Transaction(id = 1, label = "Salary", amount = 500.0, description = "Income")
        val transaction2 = Transaction(id = 2, label = "Rent", amount = -200.0, description = "Expense")

        assertEquals(green, transaction1.getColorResource(green, red))
        assertEquals(red, transaction2.getColorResource(green, red))
    }
}
