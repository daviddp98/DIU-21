import React, { Component } from "react";
import firebase from 'firebase';
import { Link } from "react-router-dom";

export default class Login extends Component {
  usuario = React.createRef();
  contraseña = React.createRef();

  constructor(props) {
    super(props);
    //Este enlace es necesario para hacer que `this` funcione en el callback
    this.login = this.login.bind(this);
    this.signup = this.signup.bind(this);
  }

  login(e) {
    e.preventDefault();
    var misusuario = this.usuario.current.value;
    var micontraseña = this.contraseña.current.value;

    firebase
      .auth()
      .signInWithEmailAndPassword(misusuario, micontraseña)
      .then(u => { })
      .catch(function (error) {
        console.log(error);
      });
  }

  signup(e) {
    e.preventDefault();
    var miusuario = this.usuario.current.value;
    var micontraseña = this.contraseña.current.value;

    firebase
      .auth()
      .createUserWithEmailAndPassword(miusuario, micontraseña)
      .then(u => { })
      .catch(function (error) {
        console.log(error);
      });
  }

  render() {
    return (
      <div className="row">
        <form>
          <div className="form-group">
            <label htmlFor="exampleInputEmail">Correo electronico:</label>
            <input
              className="form-control"
              type="email"
              name="email"
              id="exampleInputEmail"
              ref={this.usuario}
              required
            ></input>
          </div>

          <div className="form-group">
            <label htmlFor="exampleInputPassword">Contraseña:</label>
            <input
              className="form-control"
              type="password"
              name="password"
              id="exampleInputPassword"
              ref={this.contraseña}
              required
            ></input>
          </div>

          {/* <button className="m-3 btn btn-sm btn btn-success" type="submit" onClick={this.login}>
            <Route exact path={["/", "/contactos"]} />
              Login
          </button> */}

          <Link
            to={"/contactos"}
            className="m-3 btn btn-sm btn btn-success"
            type="submit"
            onClick={this.login}
          >
            Login
          </Link>

          {/* <button className="m-3 btn btn-sm btn btn-danger" onClick={this.signup}>
            Registrarse
          </button> */}
        </form>
      </div>
    );
  }
}