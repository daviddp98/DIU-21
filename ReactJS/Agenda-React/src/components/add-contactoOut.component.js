import React, { Component } from "react";

import AgendaDataService from "../services/agenda.service";

export default class AddAgenda extends Component {
  constructor(props) {
    super(props);
    this.onChangeNombre = this.onChangeNombre.bind(this);
    this.onChangeTelefono = this.onChangeTelefono.bind(this);
    this.onChangeCalle = this.onChangeCalle.bind(this);
    this.onChangeCodPostal = this.onChangeCodPostal.bind(this);
    this.onChangeCiudad = this.onChangeCiudad.bind(this);

    this.saveContacto = this.saveContacto.bind(this);
    this.newContacto = this.newContacto.bind(this);

    this.state = {
      nombre: "",
      telefono: "",
      calle: "",
      codPostal: "",
      ciudad: "",

      submitted: false,
    };
  }

  onChangeNombre(e) {
    this.setState({
      nombre: e.target.value,
    });
  }

  onChangeTelefono(e) {
    this.setState({
      telefono: e.target.value,
    });
  }

  onChangeCalle(e) {
    this.setState({
      calle: e.target.value,
    });
  }

  onChangeCodPostal(e) {
    this.setState({
      codPostal: e.target.value,
    });
  }

  onChangeCiudad(e) {
    this.setState({
      ciudad: e.target.value,
    });
  }

  saveContacto() {
    let data = {
      nombre: this.state.nombre,
      telefono: this.state.telefono,
      calle: this.state.calle,
      codPostal: this.state.codPostal,
      ciudad: this.state.ciudad
    };

    AgendaDataService.create(data)
      .then(() => {
        console.log("Se ha creado un nuevo Contacto!!!");
        this.setState({
          submitted: true,
        });
      })
      .catch((e) => {
        console.log(e);
      });
  }

  newContacto() {
    this.setState({
      nombre: "",
      telefono: "",
      calle: "",
      codPostal: "",
      ciudad: "",

      submitted: false,
    });
  }

  render() {
    return (
      <div className="submit-form">
        <div>
          <div className="form-group">
            <label htmlFor="nombre">Nombre</label>
            <input
              type="text"
              className="form-control"
              id="nombre"
              readonly="readonly"
              required min="1"
              value={this.state.nombre}
              onChange={this.onChangeNombre}
              name="nombre"
            />
          </div>

          <div className="form-group">
            <label htmlFor="telefono">Teléfono</label>
            <input
              type="number"
              className="form-control"
              id="telefono"
              readonly="readonly"
              required min="1"
              value={this.state.telefono}
              onChange={this.onChangeTelefono}
              name="telefono"
            />
          </div>

          <div className="form-group">
            <label htmlFor="calle">Calle</label>
            <input
              type="text"
              className="form-control"
              id="calle"
              readonly="readonly"
              required min="1"
              value={this.state.calle}
              onChange={this.onChangeCalle}
              name="calle"
            />
          </div>

          <div className="form-group">
            <label htmlFor="codPostal">Código Postal</label>
            <input
              type="number"
              className="form-control"
              id="codPostal"
              readonly="readonly"
              required min="1"
              value={this.state.codPostal}
              onChange={this.onChangeCodPostal}
              name="codPostal"
            />
          </div>

          <div className="form-group">
            <label htmlFor="ciudad">Ciudad</label>
            <input
              type="text"
              readonly="readonly"
              className="form-control"
              id="ciudad"
              required min="1"
              value={this.state.ciudad}
              onChange={this.onChangeCiudad}
              name="ciudad"
            />
          </div>
        </div>
      </div>
    );
  }
}