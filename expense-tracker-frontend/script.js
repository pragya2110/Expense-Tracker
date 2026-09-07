let allExpenses = []

const expenseForm =
    document.getElementById("expenseForm");

expenseForm.addEventListener(
    "submit",
    async function(event) {

        event.preventDefault();


        const id =
            document.getElementById("expenseId").value;

        const expense = {

            title:
                document.getElementById("title").value,

            amount:
                Number(
                    document.getElementById("amount").value
                ),

            category:
                document.getElementById("category").value,

            date:
                document.getElementById("date").value
        };


        let url =
            "http://localhost:8080/api/expenses";

        let method = "POST";


        // EDIT MODE
        if (id) {

            url =
                `http://localhost:8080/api/expenses/${id}`;

            method = "PUT";
        }


        try {

            const response = await fetch(
                url,
                {
                    method: method,

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body:
                        JSON.stringify(expense)
                }
            );


            const message =
                await response.text();


            if (response.ok) {

                alert(message);

                resetForm();

                loadExpenses();

            } else {

                alert(
                    "Operation failed."
                );
            }

        } catch (error) {

            console.error(error);

            alert(
                "Could not connect to backend."
            );
        }

    }
);

async function loadExpenses() {

    try {

        const response = await fetch(
            "http://localhost:8080/api/expenses"
        );

        if (!response.ok) {
            throw new Error("Failed to load expenses");
        }

        allExpenses = await response.json();

        populateCategoryFilter();

        displayExpenses(allExpenses);

    } catch (error) {

        console.error(
            "Could not load expenses:",
            error
        );
    }
}

function displayExpenses(expenses) {

    const expenseList =
        document.getElementById("expenseList");

    expenseList.innerHTML = "";

    if (expenses.length === 0) {

        expenseList.innerHTML = `
            <tr>
                <td colspan="6" class="no-expenses">
                    No expenses found.
                </td>
            </tr>
        `;

        return;
    }

    expenses.forEach(expense => {

        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${expense.id}</td>
            <td>${expense.title}</td>
            <td>₹${expense.amount.toLocaleString()}</td>
            <td>${expense.category}</td>
            <td>${expense.date}</td>

            <td>
                <button
                    onclick="editExpense(${expense.id})">
                    Edit
                </button>

                <button
                    onclick="deleteExpense(${expense.id})">
                    Delete
                </button>
            </td>
        `;

        expenseList.appendChild(row);
    });
}

async function deleteExpense(id) {

    const confirmed =
        confirm("Are you sure you want to delete this expense?");

    if (!confirmed) {
        return;
    }

    try {

        const response = await fetch(
            `http://localhost:8080/api/expenses/${id}`,
            {
                method: "DELETE"
            }
        );

        const message =
            await response.text();

        if (response.ok) {

            alert(message);

            loadExpenses();

        } else {

            alert("Failed to delete expense.");
        }

    } catch (error) {

        console.error(error);

        alert(
            "Could not connect to the backend."
        );
    }
}

async function editExpense(id) {

    try {

        const response = await fetch(
            `http://localhost:8080/api/expenses`
        );

        const expenses = await response.json();

        const expense =
            expenses.find(e => e.id === id);

        if (!expense) {

            alert("Expense not found.");

            return;
        }


        document.getElementById("expenseId").value =
            expense.id;

        document.getElementById("title").value =
            expense.title;

        document.getElementById("amount").value =
            expense.amount;

        document.getElementById("category").value =
            expense.category;

        document.getElementById("date").value =
            expense.date;


        document.getElementById("formTitle").textContent =
            "Edit Expense";

        document.getElementById("submitButton").textContent =
            "Update Expense";

        document.getElementById("cancelButton").style.display =
            "inline-block";


        window.scrollTo({
            top: 0,
            behavior: "smooth"
        });

    } catch (error) {

        console.error(error);

        alert("Could not load expense.");
    }
}

function resetForm() {

    document.getElementById("expenseForm").reset();

    document.getElementById("expenseId").value =
        "";

    document.getElementById("formTitle").textContent =
        "Add Expense";

    document.getElementById("submitButton").textContent =
        "Add Expense";

    document.getElementById("cancelButton").style.display =
        "none";
}

document
    .getElementById("cancelButton")
    .addEventListener(
        "click",
        resetForm
    );

// Load the expense table when the page first opens
loadExpenses();

async function loadTotalIncome() {

    try {

        const response = await fetch(
            "http://localhost:8080/api/income/total"
        );

        const totalIncome =
            await response.json();

        document.getElementById("totalIncome")
            .textContent =
            "₹" + totalIncome.toLocaleString();

    } catch (error) {

        console.error(
            "Could not load total income:",
            error
        );
    }
}

loadTotalIncome();

async function loadTotalExpenses() {

    try {

        const response = await fetch(
            "http://localhost:8080/api/expenses/total"
        );

        const totalExpenses =
            await response.json();

        document.getElementById("totalExpenses")
            .textContent =
            "₹" + totalExpenses.toLocaleString();

    } catch (error) {

        console.error(
            "Could not load total expenses:",
            error
        );
    }
}

loadTotalExpenses();

async function loadSavings() {

    try {

        const incomeResponse = await fetch(
            "http://localhost:8080/api/income/total"
        );

        const expenseResponse = await fetch(
            "http://localhost:8080/api/expenses/total"
        );


        const totalIncome =
            await incomeResponse.json();

        const totalExpenses =
            await expenseResponse.json();


        const savings =
            totalIncome - totalExpenses;


        document.getElementById("savings")
            .textContent =
            "₹" + savings.toLocaleString();

    } catch (error) {

        console.error(
            "Could not calculate savings:",
            error
        );
    }
}

loadSavings();


async function loadBudgetStatus() {
    try {
        const response = await fetch("http://localhost:8080/api/budgets/status");

        if (!response.ok) {
            throw new Error("Failed to load budget status");
        }

        const budgets = await response.json();

        const budgetContainer = document.getElementById("budgetContainer");

        budgetContainer.innerHTML = "";

        if (budgets.length === 0) {
            budgetContainer.innerHTML = `
                <div class="no-budget">
                    No budget data available.
                </div>
            `;
            return;
        }

        budgets.forEach(budget => {

            const percentage = budget.budgetAmount > 0
                ? (budget.spentAmount / budget.budgetAmount) * 100
                : 0;

            const barWidth = Math.min(Math.max(percentage, 0), 100);

            let statusClass = "safe";
            let statusText = "Within Budget";

            if (percentage >= 100) {
                statusClass = "danger";
                statusText = "Budget Exceeded";
            } else if (percentage >= 80) {
                statusClass = "warning";
                statusText = "Approaching Budget Limit";
            }

            const card = document.createElement("div");

            card.className = "budget-card";

            card.innerHTML = `
                <h3>${budget.category}</h3>

                <div class="budget-info">
                    <span>
                        ₹${budget.spentAmount.toLocaleString()} spent
                    </span>

                    <span>
                        of ₹${budget.budgetAmount.toLocaleString()}
                    </span>
                </div>

                <div class="progress-bar">
                    <div 
                        class="progress-fill"
                        style="width: ${barWidth}%;">
                    </div>
                </div>

                <div class="percentage-text">
                    ${percentage.toFixed(1)}% used
                </div>

                <div class="budget-status ${statusClass}">
                    ${statusText}
                </div>

                <div class="budget-remaining">
                    ₹${budget.remainingAmount.toLocaleString()} remaining
                </div>
            `;

            budgetContainer.appendChild(card);
        });

    } catch (error) {
        console.error("Could not load budget status:", error);
    }
}

loadBudgetStatus();

let categoryChart;

async function loadCategoryChart() {

    try {

        const response = await fetch(
            "http://localhost:8080/api/expenses/analytics/category"
        );

        if (!response.ok) {
            throw new Error("Failed to load category analytics");
        }

        const data = await response.json();

        const categories = data.map(item => item.category);
        const amounts = data.map(item => item.totalAmount);

        /*
         * Find the category with the highest spending.
         */
        if (data.length > 0) {

            const highest = data[0];

            document.getElementById("highestCategory").textContent =
                highest.category +
                " - ₹" +
                highest.totalAmount.toLocaleString();
        }

        const ctx = document
            .getElementById("categoryChart")
            .getContext("2d");

        if (categoryChart) {
            categoryChart.destroy();
        }

        categoryChart = new Chart(ctx, {

            type: "doughnut",

            data: {
                labels: categories,

                datasets: [
                    {
                        label: "Expenses",

                        data: amounts
                    }
                ]
            },

            options: {
                responsive: true,

                maintainAspectRatio: false,

                plugins: {
                    legend: {
                        position: "bottom"
                    }
                }
            }

        });

    } catch (error) {

        console.error(
            "Could not load category chart:",
            error
        );
    }
}

let monthlyChart;

async function loadMonthlyChart() {

    try {

        const response = await fetch(
            "http://localhost:8080/api/expenses/analytics/monthly"
        );

        if (!response.ok) {
            throw new Error("Failed to load monthly analytics");
        }

        const data = await response.json();

        const months = data.map(item => item.month);
        const amounts = data.map(item => item.totalAmount);

        const ctx = document
            .getElementById("monthlyChart")
            .getContext("2d");

        if (monthlyChart) {
            monthlyChart.destroy();
        }

        monthlyChart = new Chart(ctx, {

            type: "bar",

            data: {

                labels: months,

                datasets: [
                    {
                        label: "Monthly Expenses",

                        data: amounts
                    }
                ]
            },

            options: {

                responsive: true,

                maintainAspectRatio: false,

                scales: {

                    y: {
                        beginAtZero: true
                    }

                }

            }

        });

    } catch (error) {

        console.error(
            "Could not load monthly chart:",
            error
        );
    }
}

loadCategoryChart();
loadMonthlyChart();

async function loadAnalyticsTotalExpenses() {

    try {

        const response = await fetch(
            "http://localhost:8080/api/expenses/total"
        );

        const total = await response.json();

        document.getElementById(
            "analyticsTotalExpenses"
        ).textContent =
            "₹" + total.toLocaleString();

    } catch (error) {

        console.error(
            "Could not load analytics total:",
            error
        );
    }
}

loadAnalyticsTotalExpenses();

function populateCategoryFilter() {

    const categoryFilter =
        document.getElementById("categoryFilter");

    const categories = [
        ...new Set(
            allExpenses.map(expense => expense.category)
        )
    ];

    categoryFilter.innerHTML = `
        <option value="all">
            All Categories
        </option>
    `;

    categories.forEach(category => {

        const option =
            document.createElement("option");

        option.value = category;
        option.textContent = category;

        categoryFilter.appendChild(option);
    });
}

function filterExpenses() {

    const searchText =
        document
            .getElementById("searchExpense")
            .value
            .toLowerCase()
            .trim();

    const selectedCategory =
        document.getElementById("categoryFilter").value;

    const startDate =
        document.getElementById("startDate").value;

    const endDate =
        document.getElementById("endDate").value;


    const filteredExpenses =
        allExpenses.filter(expense => {

            /*
             * Search by title or category
             */
            const matchesSearch =
                expense.title
                    .toLowerCase()
                    .includes(searchText)
                ||
                expense.category
                    .toLowerCase()
                    .includes(searchText);


            /*
             * Category filter
             */
            const matchesCategory =
                selectedCategory === "all"
                ||
                expense.category === selectedCategory;


            /*
             * Start date filter
             */
            const matchesStartDate =
                !startDate
                ||
                expense.date >= startDate;


            /*
             * End date filter
             */
            const matchesEndDate =
                !endDate
                ||
                expense.date <= endDate;


            return (
                matchesSearch &&
                matchesCategory &&
                matchesStartDate &&
                matchesEndDate
            );
        });


    displayExpenses(filteredExpenses);
}


document
    .getElementById("searchExpense")
    .addEventListener(
        "input",
        filterExpenses
    );


document
    .getElementById("categoryFilter")
    .addEventListener(
        "change",
        filterExpenses
    );


document
    .getElementById("startDate")
    .addEventListener(
        "change",
        filterExpenses
    );


document
    .getElementById("endDate")
    .addEventListener(
        "change",
        filterExpenses
    );

document
    .getElementById("clearFilters")
    .addEventListener("click", function () {

        document.getElementById(
            "searchExpense"
        ).value = "";

        document.getElementById(
            "categoryFilter"
        ).value = "all";

        document.getElementById(
            "startDate"
        ).value = "";

        document.getElementById(
            "endDate"
        ).value = "";

        displayExpenses(allExpenses);
    });


