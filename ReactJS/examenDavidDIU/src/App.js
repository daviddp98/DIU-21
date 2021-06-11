import React, { Component } from "react";
//import { BrowserRouter as Router } from "react-router-dom";
import { Route, Switch, Link } from "react-router-dom";
//import firebase from 'firebase';

import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

import ProductosList from "./components/productos-list.component";
import AddProducto from "./components/add-producto.component";
import Producto from "./components/producto.component";

class App extends Component {
    render() {
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/products"} className="navbar-brand">
            Ferretería
          </Link>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/products"} className="nav-link">
                Productos
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/add"} className="nav-link">
                Añadir
              </Link>
            </li>
          </div>
        </nav>
        
        <div className="container mt-3">
          <Switch>
            <Route exact path={["/", "/products"]} component={ProductosList} />
            {<Route exact path="/add" component={AddProducto} />}
            {<Route path="/products/:id" component={Producto} />}
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;