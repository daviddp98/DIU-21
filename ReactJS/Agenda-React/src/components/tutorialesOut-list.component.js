import React, { Component } from "react";

import TutorialDataService from "../services/tutorial.service";
import AgendaDataService from "../services/agenda.service";

export default class TutorialsList extends Component {
  constructor(props) {
    super(props);
    this.refreshList = this.refreshList.bind(this);
    this.setActiveTutorial = this.setActiveTutorial.bind(this);
    this.onDataChange = this.onDataChange.bind(this);
    this.updateContact = this.updateContact.bind(this);

    this.getTitulo = this.getTitulo.bind(this);
    this.addTutorialToContact = this.addTutorialToContact.bind(this);

    this.state = {
      tutorials: [],
      currentContacto: props.contacto,
      currentTutorial: null,
      currentIndex: -1,
      currentIndexContacto: -1,
      tutorialsContacto: props.contacto.tutorials,
    };
  }

  componentDidMount() {
    this.retrieveTutorials();
  }

  componentWillUnmount() {
    this.retrieveTutorials();
  }

  retrieveTutorials() {
    TutorialDataService.getAll()
      .then(response => {
        this.setState({
          tutorials: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  refreshList() {
    this.retrieveTutorials();
    this.setState({
      currentTutorial: null,
      currentIndex: -1,
      tutorialsContacto: this.contacto.tutorials,
    });
  }

  addTutorials() {
    AgendaDataService.create()
      .then(() => {
        this.refreshList();
      })
      .catch((e) => {
        console.log(e);
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

  onChangeTutorials(e) {
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

  onDataChange(items) {
    let tutorials = [];

    items.forEach((item) => {
      let data = item.val();
      tutorials.push({
        titulo: data.titulo,
        descripcion: data.descripcion,
        publicado: data.publicado,
      });
    });

    this.setState({
      tutorials: tutorials,
    });
  }

  addTutorialToContact(currentTutorial) {
    if (this.props.contacto.tutorials == null) {
      this.props.contacto.tutorials = []
    }

    this.props.contacto.tutorials.push({
      id: currentTutorial.id
    });

    this.updateContact();
  }

  getTitulo(tutorial, tutorials) {
    var i = 0;
    for (i = 0; i < tutorials.length; i++) {
      if (tutorial.id === tutorials[i].id) {
        return tutorials[i].titulo
      }
    }
  }

  updateContact() {
    const data = {
      nombre: this.props.contacto.nombre,
      telefono: this.props.contacto.telefono,
      calle: this.props.contacto.calle,
      codPostal: this.props.contacto.codPostal,
      ciudad: this.props.contacto.ciudad,
      tutorials: this.props.contacto.tutorials,
    }

    AgendaDataService.update(this.props.contacto.key, data)
      .then(() => {
        this.setState({
          message: "El Tutorial ha sido añadido al Contacto!!"
        });
      })
      .catch(e => {
        console.log(e);
      });
  }

  setActiveTutorial(tutorial, index) {
    this.setState({
      currentTutorial: tutorial,
      currentIndex: index
    });
  }

  render() {
    const { tutorials, currentIndex } = this.state;

    return (
      <div className="row">
        <div className="col-md-6">
          <h4>Tutoriales</h4>

          <ul className="list-group">
            {this.props.contacto.tutorials &&
              this.props.contacto.tutorials.map((tutorial, index) => (
                <li
                  className={
                    "list-group-item " +
                    (index === currentIndex ? "active" : "")
                  }
                  key={index}
                >
                  {this.getTitulo(tutorial, tutorials)}
                </li>
              ))}
          </ul>
        </div>
      </div>
    );
  }
}