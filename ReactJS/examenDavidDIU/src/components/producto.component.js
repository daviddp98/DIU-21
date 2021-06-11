import React, { Component } from "react";

import ProductoDataService from "../services/producto.service";
import Total from "./total.component";
import Unidades from "./unidades.component";

export default class Producto extends Component {
  constructor(props) {
    super(props);
    this.onChangeStock = this.onChangeStock.bind(this);
    this.onChangeName = this.onChangeName.bind(this);
    this.onChangeBrand = this.onChangeBrand.bind(this);
    this.onChangePrice = this.onChangePrice.bind(this);

    //this.getProducto = this.getProducto.bind(this);
    this.updateActived = this.updateActived.bind(this);
    this.updateProducto = this.updateProducto.bind(this);
    this.deleteProducto = this.deleteProducto.bind(this);
    this.actualizar = this.actualizar.bind(this);


    this.state = {
      currentProducto: {
        id: null,
        stock: "",
        name: "",
        brand: "",
        price: "",
        active: false
      },
      message: ""
    };
  }

  static getDerivedStateFromProps(nextProps, prevState) {
    const { producto } = nextProps;
    if (prevState.currentProducto.id !== producto.id) {
      return {
        currentProducto: producto,
        message: ""
      };
    }

    return prevState.currentProducto;
  }

  componentDidMount() {
    this.setState({
      currentProducto: this.props.producto,
    });
    console.log(this.state.currentProducto);
  }


  onChangeStock(e) {
    const stock = e.target.value;

    this.setState(function (prevState) {
      return {
        currentProducto: {
          ...prevState.currentProducto,
          stock: stock
        }
      };
    });
  }

  onChangeName(e) {
    const name = e.target.value;

    this.setState(prevState => ({
      currentProducto: {
        ...prevState.currentProducto,
        name: name
      }
    }));
  }

  onChangeBrand(e) {
    const brand = e.target.value;

    this.setState(prevState => ({
      currentProducto: {
        ...prevState.currentProducto,
        brand: brand
      }
    }));
  }

  onChangePrice(e) {
    const price = e.target.value;

    this.setState(prevState => ({
      currentProducto: {
        ...prevState.currentProducto,
        price: price
      }
    }));
  }

  /* getProducto(id) {
    ProductoDataService.getProductById(id)
      .then(response => {
        this.setState({
          currentProducto: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  } */

  updateActived(status) {
    ProductoDataService.update({
      id: this.state.currentProducto.id,
      name: this.state.currentProducto.name,
      stock: this.state.currentProducto.stock,
      brand: this.state.currentProducto.brand,
      price: this.state.currentProducto.price,
      active: status,
    })
      .then(() => {
        this.setState((prevState) => ({
          currentProducto: {
            ...prevState.currentProducto,
            active: status,
          },
          message: "El estado del producto ha sido actualizado.",
        }));
      })
      .catch((e) => {
        console.log(e);
      });
  }

  actualizar(e) {
    const data = {
      id: this.state.currentProducto.id,
      stock: e,
      name: this.state.currentProducto.name,
      brand: this.state.currentProducto.brand,
      price: this.state.currentProducto.price,
      active: this.state.currentProducto.active
    };

    ProductoDataService.update(data)
      .then(() => {
        this.setState({
          message: "El producto ha sido actualizado.",
        });
      })
      .catch((e) => {
        console.log(e);
      });
  }

  updateProducto() {
    const data = {
      id: this.state.currentProducto.id,
      stock: this.state.currentProducto.stock,
      name: this.state.currentProducto.name,
      brand: this.state.currentProducto.brand,
      price: this.state.currentProducto.price,
      active: this.state.currentProducto.active
    };

    ProductoDataService.update(data)
      .then(() => {
        this.setState({
          message: "El producto ha sido actualizado.",
        });
        this.props.refreshList();
      })
      .catch((e) => {
        console.log(e);
      });
  }

  deleteProducto() {
    ProductoDataService.delete(this.state.currentProducto.id)
      .then(() => {
        this.props.refreshList();
      })
      .catch((e) => {
        console.log(e);
      });
  }

  render() {
    const { currentProducto } = this.state;

    return (
      <div>
        {currentProducto ? (
          <div className="edit-form">
            <h4>Producto</h4>
            <form>
              <div className="form-group">
                <label htmlFor="stock">Stock</label>
                <input
                  type="number"
                  className="form-control"
                  id="stock"
                  value={currentProducto.stock}
                  onChange={this.onChangeStock}
                />
              </div>

              <div className="form-group">
                <label htmlFor="name">Name</label>
                <input
                  type="text"
                  className="form-control"
                  id="name"
                  value={currentProducto.name}
                  onChange={this.onChangeName}
                />
              </div>

              <div className="form-group">
                <label htmlFor="brand">Brand</label>
                <input
                  type="text"
                  className="form-control"
                  id="brand"
                  value={currentProducto.brand}
                  onChange={this.onChangeBrand}
                />
              </div>

              <div className="form-group">
                <label htmlFor="price">Price</label>
                <input
                  type="number"
                  className="form-control"
                  id="price"
                  value={currentProducto.price}
                  onChange={this.onChangePrice}
                />
              </div>

              <div className="form-group">
                <label>
                  <strong>Active: </strong>
                </label>
                {currentProducto.active ? " Active" : " Desactive"}
              </div>
            </form>


            {currentProducto.active ? (
              <button
                className="badge badge-primary mr-2"
                onClick={() => this.updateActived(false)}
              >
                Desactivar
              </button>
            ) : (
              <button
                className="badge badge-primary mr-2"
                onClick={() => this.updateActived(true)}
              >
                Activar
              </button>
            )}

            <button
              className="badge badge-danger mr-2"
              onClick={this.deleteProducto}
            >
              Borrar
            </button>

            {currentProducto.active ? (
              <p>
                <button
                  type="submit"
                  className="badge badge-success"
                  onClick={this.updateProducto}
                >
                  Modificar
                </button>

                <Total
                  stock={this.state.currentProducto.stock}
                  price={this.state.currentProducto.price}>
                </Total>

                <Unidades
                  actualizar={this.actualizar}
                  stock={this.state.currentProducto.stock}
                >
                </Unidades>
              </p>
            ) : (
              <div>
                <br />
                <p>**Para poder modificar el producto debe de estar Active: Active</p>
              </div>
            )}
          </div>
        ) : (
          <div>
            <br />
            <p>Por favor, selecciona un Producto...</p>
          </div>
        )}
      </div>
    );
  }
}