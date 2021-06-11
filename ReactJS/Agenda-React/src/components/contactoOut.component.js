import React, { Component } from "react";

import AgendaDataService from "../services/agenda.service";

export default class Contacto extends Component {
  constructor(props) {
    super(props);
    this.onChangeNombre = this.onChangeNombre.bind(this);
    this.onChangeTelefono = this.onChangeTelefono.bind(this);
    this.onChangeCalle = this.onChangeCalle.bind(this);
    this.onChangeCodPostal = this.onChangeCodPostal.bind(this);
    this.onChangeCiudad = this.onChangeCiudad.bind(this);
    this.onChangeTutoriales = this.onChangeTutoriales.bind(this);

    this.updateContacto = this.updateContacto.bind(this);
    this.deleteContacto = this.deleteContacto.bind(this);

    this.state = {
      currentContacto: {
        key: null,
        nombre: "",
        telefono: "",
        calle: "",
        codPostal: "",
        ciudad: "",
        tutoriales: [],
      },
      message: "",
    };
  }

  static getDerivedStateFromProps(nextProps, prevState) {
    const { contacto } = nextProps;
    if (prevState.currentContacto.key !== contacto.key) {
      return {
        currentContacto: contacto,
        message: ""
      };
    }
    return prevState.currentContacto;
  }

  componentDidMount() {
    this.setState({
      currentContacto: this.props.contacto,
    });
  }

  onChangeNombre(e) {
    const nombre = e.target.value;

    this.setState(function (prevState) {
      return {
        currentContacto: {
          ...prevState.currentContacto,
          nombre: nombre,
        },
      };
    });
  }

  onChangeTelefono(e) {
    const telefono = e.target.value;

    this.setState(function (prevState) {
      return {
        currentContacto: {
          ...prevState.currentContacto,
          telefono: telefono,
        },
      };
    });
  }

  onChangeCalle(e) {
    const calle = e.target.value;

    this.setState(function (prevState) {
      return {
        currentContacto: {
          ...prevState.currentContacto,
          calle: calle,
        },
      };
    });
  }

  onChangeCodPostal(e) {
    const codPostal = e.target.value;

    this.setState(function (prevState) {
      return {
        currentContacto: {
          ...prevState.currentContacto,
          codPostal: codPostal,
        },
      };
    });
  }

  onChangeCiudad(e) {
    const ciudad = e.target.value;

    this.setState(function (prevState) {
      return {
        currentContacto: {
          ...prevState.currentContacto,
          ciudad: ciudad,
        },
      };
    });
  }

  onChangeTutoriales(e) {
    const tutorials = e.target.value;

    this.setState(function (prevState) {
      return {
        currentContacto: {
          ...prevState.currentContacto,
          tutorials: tutorials,
        },
      };
    });
  }

  updateContacto() {
    const data = {
      nombre: this.state.currentContacto.nombre,
      telefono: this.state.currentContacto.telefono,
      calle: this.state.currentContacto.calle,
      codPostal: this.state.currentContacto.codPostal,
      ciudad: this.state.currentContacto.ciudad,
    };

    AgendaDataService.update(this.state.currentContacto.key, data)
      .then(() => {
        this.setState({
          message: "El contacto ha sido modificado!!!",
        });
      })
      .catch((e) => {
        console.log(e);
      });
  }

  deleteContacto() {
    AgendaDataService.delete(this.state.currentContacto.key)
      .then(() => {
        this.props.refreshList();
      })
      .catch((e) => {
        console.log(e);
      });
  }

  render() {
    const { currentContacto } = this.state;

    return (
      <div>
        <h4>Contacto</h4>
        {currentContacto ? (
          <div className="edit-form">
            <form>
              <div className="form-group">
                <label htmlFor="nombre">Nombre</label>
                <input
                  type="text"
                  className="form-control"
                  id="nombre"
                  readonly="readonly"
                  value={currentContacto.nombre}
                  onChange={this.onChangeNombre}
                />
              </div>

              <div className="form-group">
                <label htmlFor="telefono">Teléfono</label>
                <input
                  type="text"
                  className="form-control"
                  id="telefono"
                  readonly="readonly"
                  value={currentContacto.telefono}
                  onChange={this.onChangeTelefono}
                />
              </div>

              <div className="form-group">
                <label htmlFor="calle">Calle</label>
                <input
                  type="text"
                  className="form-control"
                  id="calle"
                  readonly="readonly"
                  value={currentContacto.calle}
                  onChange={this.onChangeCalle}
                />
              </div>

              <div className="form-group">
                <label htmlFor="codPostal">Código Postal</label>
                <input
                  type="text"
                  className="form-control"
                  id="codPostal"
                  readonly="readonly"
                  value={currentContacto.codPostal}
                  onChange={this.onChangeCodPostal}
                />
              </div>

              <div className="form-group">
                <label htmlFor="ciudad">Ciudad</label>
                <input
                  type="text"
                  className="form-control"
                  id="ciudad"
                  readonly="readonly"
                  value={currentContacto.ciudad}
                  onChange={this.onChangeCiudad}
                />
              </div>

            </form>
            <p>{this.state.message}</p>
          </div>
        ) : (
            <div>
              <br />
              <p>Selecciona un contacto...</p>
            </div>
          )}
      </div>
    );
  }
}