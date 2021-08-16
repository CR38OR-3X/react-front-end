import React, { useState, useEffect } from "react";
import { Jumbotron, Container } from "reactstrap";
//import Logo from './logo.svg'
import Form from "../Forms/Form";
import List from "../Forms/List";

const ALL_EXPENSES = localStorage.getItem('expenses')
  ? JSON.parse(localStorage.getItem('expenses'))
  : []

function AcctApp() {
  const [expenses, setExpenses] = useState(ALL_EXPENSES);

  const [name, setName] = useState("");
  const [amount, setAmount] = useState("");

  const handleName = (event) => {
    //console.log("Name ", event.target.value);
    setName(event.target.value);
  };

  const handleAmount = (event) => {
    //console.log("Amount ", event.target.value);
    setAmount(event.target.value);
  };

  const handleSubmitForm = (event) => {
    event.preventDefault();
    //check whether the name is not empty and the amount is not negative
    if (name !== "" && amount > 0) {
      // single expense object
      const expense = { name, amount };
      // do not override previous values in the array
      // use spread operator to access previous values
      setExpenses([...expenses, expense]);

      // clean input fields
      setName("");
      setAmount("");
    } else {
      console.log("Invalid expense name or the amount");
    }
  };

  useEffect(() => {
    localStorage.setItem('expenses', JSON.stringify(expenses))
  }, [expenses])

  const handleClearExpenses = () => {
    setExpenses([])
  }

  return (
    <Container className="text-center">
      <Jumbotron fluid>
        <h3 className="display-6">
          Transactions
          {/* <img src={Logo} style={{ width: 50, height: 50 }} alt='react-logo' /> */}
        </h3>
        <div>
          <p>
            Balance:{" "}
            <span className="text-success">
              ${" "}
              {expenses.reduce((accumulator, currentValue) => {
                return (accumulator += parseInt(currentValue.amount));
              }, 0)}
            </span>
          </p>
        </div>
        <Form
          name={name}
          amount={amount}
          handleName={handleName}
          handleAmount={handleAmount}
          handleSubmitForm={handleSubmitForm}
          handleClearExpenses={handleClearExpenses}
        />
        <List expenses={expenses} />
      </Jumbotron>
    </Container>
  );
}

export default AcctApp;
