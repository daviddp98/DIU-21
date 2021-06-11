import React, { Component } from "react";

import ProductoDataService from "../services/producto.service";

export default class AddProducto extends Component {
  constructor(props) {
    super(props);
    this.onChangeStock = this.onChangeStock.bind(this);
    this.onChangeName = this.onChangeName.bind(this);
    this.onChangeBrand = this.onChangeBrand.bind(this);
    this.onChangePrice = this.onChangePrice.bind(this);

    this.saveProducto = this.saveProducto.bind(this);
    this.newProducto = this.newProducto.bind(this);

    this.state = {
      id: null,
      stock: "",
      name: "",
      brand: "",
      price: "",
      active: false,

      submitted: false
    };
  }

  onChangeStock(e) {
    this.setState({
      stock: e.target.value
    });
  }

  onChangeName(e) {
    this.setState({
      name: e.target.value
    });
  }

  onChangeBrand(e) {
    this.setState({
      brand: e.target.value
    });
  }

  onChangePrice(e) {
    this.setState({
      price: e.target.value
    });
  }

  saveProducto() {
    var data = {
      stock: this.state.stock,
      name: this.state.name,
      brand: this.state.brand,
      price: this.state.price

    };

    ProductoDataService.create(data)
      .then(response => {
        this.setState({
          id: response.data.id,
          stock: response.data.stock,
          name: response.data.name,
          brand: response.data.brand,
          price: response.data.price,
          active: response.data.active,

          submitted: true
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  newProducto() {
    this.setState({
      id: null,
      stock: "",
      name: "",
      brand: "",
      price: "",
      active: false,

      submitted: false
    });
  }

  render() {
    return (
      <div className="submit-form">
        {this.state.submitted ? (
          <div>
            <h4>Se ha creado correctamente!!</h4>
            <button className="btn btn-success" onClick={this.newProducto}>
              Añadir
            </button>
          </div>
        ) : (
            <div>
              <div className="form-group">
                <label htmlFor="stock">Stock</label>
                <input
                  type="number"
                  className="form-control"
                  id="stock"
                  required
                  value={this.state.stock}
                  onChange={this.onChangeStock}
                  name="stock"
                />
              </div>

              <div className="form-group">
                <label htmlFor="name">Name</label>
                <input
                  type="text"
                  className="form-control"
                  id="name"
                  required
                  value={this.state.name}
                  onChange={this.onChangeName}
                  name="name"
                />
              </div>

              <div className="form-group">
                <label htmlFor="brand">Brand</label>
                <input
                  type="text"
                  className="form-control"
                  id="brand"
                  required
                  value={this.state.brand}
                  onChange={this.onChangeBrand}
                  name="brand"
                />
              </div>

              <div className="form-group">
                <label htmlFor="price">Price</label>
                <input
                  type="number"
                  className="form-control"
                  id="price"
                  required
                  value={this.state.price}
                  onChange={this.onChangePrice}
                  name="price"
                />
              </div>

              <button onClick={this.saveProducto} className="btn btn-success">
                Enviar
            </button>
            </div>
          )}
      </div>
    );
  }
}