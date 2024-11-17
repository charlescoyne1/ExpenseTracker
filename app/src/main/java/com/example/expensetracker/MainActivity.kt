package com.example.expensetracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var transactions : ArrayList<Transaction>
    private lateinit var transactionAdapter : TransactionAdapter
    private lateinit var linearLayoutManager : LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        transactions = arrayListOf(
            Transaction("Weekend budget", 400.00),
            Transaction("Bananas", -4.00),
            Transaction("Gasoline", -40.90),
            Transaction("Breakfast", -9.99),
            Transaction("Water bottles", -4.00),
            Transaction("Suncream", -8.00),
            Transaction("Car Park", -15.00)
        )

        transactionAdapter = TransactionAdapter(transactions)
        linearLayoutManager = LinearLayoutManager(this)

        binding.recyclerview.apply {
            adapter = transactionAdapter
            layoutManager = linearLayoutManager
        }

        updateDashboard()

        binding.addBtn.setOnClickListener {
            val intent = Intent(this, AddTransactionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateDashboard() {
        val totalAmount = transactions.map { it.amount }.sum()
        val budgetAmount = transactions.filter { it.amount > 0 }.map { it.amount }.sum()
        val expenseAmount = totalAmount - budgetAmount

        binding.balance.text = "$ %.2f".format(totalAmount)
        binding.budget.text = "$ %.2f".format(budgetAmount)
        binding.expense.text = "$ %.2f".format(expenseAmount)
    }
}