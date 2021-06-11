import logo from './logo.svg';
import './App.css';
import HelloComponent from './components/HelloComponent';
import InputComponent from './components/InputComponent';
import IterarLista from './components/IterarLista';
import React, { Component } from 'react';

class App extends Component {
  constructor() {
    super()
    this.state = {
      name: 'David',
      n: 3
    }
  }

  changeName = (event) => {
    this.setState({
      name: event.target.value
    })
  }

  changeNum = (event) => {
    this.setState({
      n: event.target.value
    })
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <HelloComponent nombre={this.state.name}></HelloComponent>
          <InputComponent name={this.state.name} cambiarNombre={this.changeName}></InputComponent>
          <InputComponent name={this.state.n} cambiarNombre={this.changeNum}></InputComponent>
          <IterarLista numbers={this.state.n}></IterarLista>
        </header>
      </div>
    );
  }
}

export default App;