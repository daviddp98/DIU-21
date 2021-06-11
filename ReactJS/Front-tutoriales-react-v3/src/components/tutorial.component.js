import React, { Component } from "react";
import TutorialDataService from "../services/tutorial.service";

export default class Tutorial extends Component {
  constructor(props) {
    super(props);
    this.onChangeTitle = this.onChangeTitle.bind(this);
    this.onChangeDescription = this.onChangeDescription.bind(this);
    this.getTutorial = this.getTutorial.bind(this);
    this.updatePublished = this.updatePublished.bind(this);
    this.updateTutorial = this.updateTutorial.bind(this);
    this.deleteTutorial = this.deleteTutorial.bind(this);

    this.state = {
      currentTutorial: {
        id: null,
        titulo: "",
        descripcion: "",
        publicado: false
      },
      message: ""
    };
  }

  componentDidMount() {
    this.getTutorial(this.props.match.params.id);
  }

  onChangeTitle(e) {
    const titulo = e.target.value;

    this.setState(function (prevState) {
      return {
        currentTutorial: {
          ...prevState.currentTutorial,
          titulo: titulo
        }
      };
    });
  }

  onChangeDescription(e) {
    const descripcion = e.target.value;

    this.setState(prevState => ({
      currentTutorial: {
        ...prevState.currentTutorial,
        descripcion: descripcion
      }
    }));
  }

  getTutorial(id) {
    TutorialDataService.get(id)
      .then(response => {
        this.setState({
          currentTutorial: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  updatePublished(status) {
    var data = {
      id: this.state.currentTutorial.id,
      titulo: this.state.currentTutorial.titulo,
      descripcion: this.state.currentTutorial.descripcion,
      publicado: status
    };

    TutorialDataService.update(/*this.state.currentTutorial.id,*/ data)
      .then(response => {
        this.setState(prevState => ({
          message: "El tutorial ha sido modificado correctamente!!",
          currentTutorial: {
            ...prevState.currentTutorial,
            publicado: status
          }
        }));
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  updateTutorial() {
    TutorialDataService.update(
      //this.state.currentTutorial.id,
      this.state.currentTutorial
    )
      .then(response => {
        console.log(response.data);
        this.setState({
          message: "El tutorial ha sido modificado correctamente!!"
        });
      })
      .catch(e => {
        console.log(e);
      });
  }

  deleteTutorial() {
    TutorialDataService.delete(this.state.currentTutorial.id)
      .then(response => {
        console.log(response.data);
        this.props.history.push('/tutoriales')
      })
      .catch(e => {
        console.log(e);
      });
  }

  render() {
    const { currentTutorial } = this.state;

    return (
      <div>
        {currentTutorial ? (
          <div className="edit-form">
            <h4>Tutorial</h4>
            <form>
              <div className="form-group">
                <label htmlFor="titulo">Título</label>
                <input
                  type="text"
                  className="form-control"
                  id="titulo"
                  value={currentTutorial.titulo}
                  onChange={this.onChangeTitle}
                />
              </div>
              <div className="form-group">
                <label htmlFor="descripcion">Descripción</label>
                <input
                  type="text"
                  className="form-control"
                  id="descripcion"
                  value={currentTutorial.descripcion}
                  onChange={this.onChangeDescription}
                />
              </div>

              <div className="form-group">
                <label>
                  <strong>Estado: </strong>
                </label>
                {currentTutorial.publicado ? "Publicado" : "Descatalogado"}
              </div>
            </form>

            {currentTutorial.publicado ? (
              <button
                className="badge badge-primary mr-2"
                onClick={() => this.updatePublished(false)}
              >
                Descatalogado
              </button>
            ) : (
                <button
                  className="badge badge-primary mr-2"
                  onClick={() => this.updatePublished(true)}
                >
                  Publicado
                </button>
              )}

            <button
              className="badge badge-danger mr-2"
              onClick={this.deleteTutorial}
            >
              Borrar
            </button>

            <button
              type="submit"
              className="badge badge-success"
              onClick={this.updateTutorial}
            >
              Modificar
            </button>
            <p>{this.state.message}</p>
          </div>
        ) : (
            <div>
              <br />
              <p>Por favor, selecciona un Tutorial...</p>
            </div>
          )}
      </div>
    );
  }
}