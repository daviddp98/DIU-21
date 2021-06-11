import React, { Component } from "react";
import TutorialDataService from "../services/tutorial.service";

export default class AddTutorial extends Component {
    constructor(props) {
        super(props);
        this.onChangeTitle = this.onChangeTitle.bind(this);
        this.onChangeDescription = this.onChangeDescription.bind(this);
        this.saveTutorial = this.saveTutorial.bind(this);
        this.newTutorial = this.newTutorial.bind(this);

        this.state = {
            id: null,
            titulo: "",
            descripcion: "",
            publicado: false,
            submitted: false
        };
    }

    onChangeTitle(e) {
        this.setState({
            titulo: e.target.value
        });
    }

    onChangeDescription(e) {
        this.setState({
            descripcion: e.target.value
        });
    }

    saveTutorial() {
        var data = {
            titulo: this.state.titulo,
            descripcion: this.state.descripcion
        };

        TutorialDataService.create(data)
            .then(response => {
                this.setState({
                    id: response.data.id,
                    titulo: response.data.titulo,
                    descripcion: response.data.descripcion,
                    publicado: response.data.publicado,

                    submitted: true
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    newTutorial() {
        this.setState({
            id: null,
            titulo: "",
            descripcion: "",
            publicado: false,

            submitted: false
        });
    }

    render() {
        return (
            <div className="submit-form">
                {this.state.submitted ? (
                    <div>
                        <h4>Se ha creado correctamente!!</h4>
                        <button className="btn btn-success" onClick={this.newTutorial}>
                            Añadir
            </button>
                    </div>
                ) : (
                        <div>
                            <div className="form-group">
                                <label htmlFor="titulo">Título</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="titulo"
                                    required
                                    value={this.state.titulo}
                                    onChange={this.onChangeTitle}
                                    name="titulo"
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="descripcion">Descripción</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="descripcion"
                                    required
                                    value={this.state.descripcion}
                                    onChange={this.onChangeDescription}
                                    name="descripcion"
                                />
                            </div>

                            <button onClick={this.saveTutorial} className="btn btn-success">
                                Enviar
            </button>
                        </div>
                    )}
            </div>
        );
    }
}