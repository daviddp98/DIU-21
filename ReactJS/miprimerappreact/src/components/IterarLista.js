import React, { Component } from 'react';

export default class IterarLista extends Component {

    render() {
        const numbers = [1, 2, 3];
        const listItem = numbers.map((number) =>
            <li>{number * this.props.numbers}</li>
        );
        return (
            <ul>{listItem}</ul>
        );
    }
}