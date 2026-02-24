
let currencies = [];

window.onload = function () {

    loadCurrencies();
};


function loadCurrencies() {

    fetch("/api/currencies")
        .then(response => response.json())
        .then(data => {

            currencies = data;

            let from = document.getElementById("fromCurrency");
            let to = document.getElementById("toCurrency");

            data.forEach(currency => {

                let option1 = document.createElement("option");
                option1.value = currency.name;
                option1.text = currency.name;

                let option2 = document.createElement("option");
                option2.value = currency.name;
                option2.text = currency.name;

                from.appendChild(option1);
                to.appendChild(option2);

            });

            from.value = "USD";
            to.value = "EUR";

        });
}



function convert() {

    let from = document.getElementById("fromCurrency").value;
    let to = document.getElementById("toCurrency").value;
    let amount = document.getElementById("amount").value;

    document.getElementById("loading").innerText =
        "Loading...";

    document.getElementById("error").innerText =
        "";

    fetch("/api/currency-converter", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

       body: JSON.stringify({

           from: from,
           to: to,
           value: amount

       })

    })
        .then(response => {

            if (!response.ok) {

                throw new Error("Conversion failed");
            }

            return response.json();

        })

        .then(result => {

            document.getElementById("loading").innerText =
                "";

            document.getElementById("result").innerText =
                "Result: " + result + " " + to;

        })

        .catch(error => {

            document.getElementById("loading").innerText =
                "";

            document.getElementById("error").innerText =
                "Error: Conversion failed";

        });

}