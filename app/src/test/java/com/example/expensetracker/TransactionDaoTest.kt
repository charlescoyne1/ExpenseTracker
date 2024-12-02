import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.expensetracker.AppDatabase
import com.example.expensetracker.Transaction
import com.example.expensetracker.TransactionDao
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlinx.coroutines.runBlocking
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class TransactionDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var transactionDao: TransactionDao

    @Before
    fun setup() {
        // Create an in-memory database
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        transactionDao = database.transactionDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAndRetrieveTransaction() = runBlocking {
        val transaction = Transaction(
            id = 0,
            label = "Groceries",
            amount = -50.0,
            description = "Weekly shopping"
        )

        // Use suspend functions within runBlocking
        transactionDao.insertAll(transaction)

        val transactions = transactionDao.getAll()

        assertEquals(1, transactions.size)
        assertEquals("Groceries", transactions[0].label)
        assertEquals(-50.0, transactions[0].amount, 0.0)
    }
}
