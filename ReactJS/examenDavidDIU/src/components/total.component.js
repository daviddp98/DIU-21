import React, { Component } from "react";

export default class Total extends Component {
    constructor(props) {
        super(props);
        this.onChangeUnidades = this.onChangeUnidades.bind(this);

        this.state = {
            unidades: "",
        }
    }

    onChangeUnidades(e) {
        this.setState({
            unidades: e.target.value,
        });
    }

    render() {
        return (
            <div className="list row">
                <div className="col-md-6">

                    <label>
                        <strong>Unidades:</strong>
                    </label>

                    <form className="form-group">
                        <div>
                            <input
                                type="number"
                                max={this.props.stock}
                                className="form-control"
                                id="stock"
                                required
                                value={this.state.unidades}
                                onChange={this.onChangeUnidades}
                                name="stock"
                            />
                        </div>
                    </form>
                </div>

                {this.state.unidades > this.props.stock ? (
                    <h6>No hay Stock suficiente</h6>
                ) : (
                    <div className="col-md-6">
                        <label>
                            <strong>Total:</strong>
                            <h6>{this.props.price * this.state.unidades}€</h6>
                        </label>
                    </div>
                )}
            </div>
        );
    }
}