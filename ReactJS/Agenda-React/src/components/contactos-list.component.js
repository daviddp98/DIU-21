import React, { Component } from "react";
import { ProgressBar } from 'primereact/progressbar';
import { Link } from "react-router-dom";


import AgendaDataService from "../services/agenda.service";

import Contacto from "./contacto.component";
import AddContacto from "./add-contacto.component";
import TutorialsList from "./tutoriales-list.component";

export default class ContactosList extends Component {
  constructor(props) {
    super(props);
    this.refreshList = this.refreshList.bind(this);
    this.setActiveContacto = this.setActiveContacto.bind(this);
    this.removeAllContactos = this.removeAllContactos.bind(this);
    this.onDataChange = this.onDataChange.bind(this);

    this.newContacto = this.newContacto.bind(this);

    this.state = {
      contactos: [],
      currentContacto: null,
      currentIndex: -1,
    };
  }

  componentDidMount() {
    AgendaDataService.getAll().on("value", this.onDataChange);
  }

  componentWillUnmount() {
    AgendaDataService.getAll().off("value", this.onDataChange);
  }

  newContacto() {
    this.setState({
      nombre: "",
      telefono: "",
      calle: "",
      codPostal: "",
      ciudad: "",
      tutorials: [],

      submitted: false,
    });
  }

  onDataChange(items) {
    let contactos = [];

    items.forEach((item) => {
      let key = item.key;
      let data = item.val();

      contactos.push({
        key: key,
        nombre: data.nombre,
        telefono: data.telefono,
        calle: data.calle,
        codPostal: data.codPostal,
        ciudad: data.ciudad,
        tutorials: data.tutorials
      });
    });

    this.setState({
      contactos: contactos,
    });
  }

  refreshList() {
    this.setState({
      currentContacto: null,
      currentIndex: -1,
    });
  }

  setActiveContacto(contacto, index) {
    this.setState({
      currentContacto: contacto,
      currentIndex: index,
    });
  }

  removeAllContactos() {
    AgendaDataService.deleteAll()
      .then(() => {
        this.refreshList();
      })
      .catch((e) => {
        console.log(e);
      });
  }

  render() {
    const { contactos, currentContacto, currentIndex } = this.state;

    return (
      <div className="row">
        <div className="col-md-4">
          <h4>Lista de Contactos</h4>

          <div className="col-md-5">
            <ProgressBar value={contactos.length * 2}></ProgressBar>
          </div>

          <br></br>

          <ul className="list-group">
            {contactos &&
              contactos.map((contacto, index) => (
                <li
                  className={
                    "list-group-item " +
                    (index === currentIndex ? "active" : "")
                  }
                  onClick={() => this.setActiveContacto(contacto, index)}
                  key={index}
                >
                  {contacto.nombre}
                </li>
              ))}
          </ul>

          <button
            className="m-3 btn btn-sm btn-danger"
            onClick={this.removeAllContactos}
          >
            Eliminar Todos
          </button>

          <button
            className="m-3 btn btn-sm btn btn-success"
            onClick={this.refreshList}
          >
            Añadir
          </button>

          <Link
            to={"/loginAdmin"}
            className="m-3 btn btn-sm btn btn-primary"
          >
            Administrar Usuarios
          </Link>
        </div>

        <div className="col-md-4">
          {currentContacto ? (
            <Contacto
              contacto={currentContacto}
              refreshList={this.refreshList}
            />
          ) : (
            <div>
              <AddContacto
                contacto={currentContacto}
                refreshList={this.refreshList}
              />
            </div>
          )}
        </div>

        <div className="col-md-4">
          {currentContacto ? (
            <TutorialsList
              contacto={currentContacto}
            />
          ) : (
            <div>
              <h5>Selecciona un Contacto...</h5>
            </div>
          )}
        </div>
      </div>
    );
  }
}