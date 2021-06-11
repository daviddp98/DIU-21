import React, { Component } from "react";
import ProductoDataService from "../services/producto.service";
import Producto from "../components/producto.component";

export default class ProductosList extends Component {
  constructor(props) {
    super(props);
    this.retrieveProducts = this.retrieveProducts.bind(this);
    this.refreshList = this.refreshList.bind(this);
    this.setActiveProducto = this.setActiveProducto.bind(this);

    this.state = {
      productos: [],
      currentProducto: null,
      currentIndex: -1,
    };
  }

  componentDidMount() {
    this.retrieveProducts();
  }

  retrieveProducts() {
    ProductoDataService.getAll()
      .then(response => {
        this.setState({
          productos: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  refreshList() {
    this.retrieveProducts();
    this.setState({
      currentProducto: null,
      currentIndex: -1
    });
  }

  setActiveProducto(producto, index) {
    this.setState({
      currentProducto: producto,
      currentIndex: index
    });
  }

  render() {
    const { productos, currentProducto, currentIndex } = this.state;

    return (
      <div className="row">
        <div className="col-md-2">

          <h7><b>Nombre</b></h7>
          <ul className="list-group">
            {productos &&
              productos.map((producto, index) => (
                <li
                  className={
                    "list-group-item " +
                    (index === currentIndex ? "active" : "")
                  }
                  onClick={() => this.setActiveProducto(producto, index)}
                  key={index}
                >
                  {producto.name}
                </li>
              ))}
          </ul>
          </div>

          <div className="col-md-2">
            <h7><b>Precio</b></h7>
            <ul className="list-group">
              {productos &&
                productos.map((producto, index) => (
                  <li
                    className={
                      "list-group-item " +
                      (index === currentIndex ? "active" : "")
                    }
                    onClick={() => this.setActiveProducto(producto, index)}
                    key={index}
                  >
                    {producto.price}€
                  </li>
                ))}
            </ul>

          </div>

          <div className="col-md-4">
            {currentProducto ? (
              <Producto
                producto={currentProducto}
                refreshList={this.refreshList}
              />
            ) : (
              <div>
                <br />
                <p>Por favor, selecciona un Producto...</p>
              </div>
            )}

          </div>
        </div>
    );
  }
}