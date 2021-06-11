import React from 'react';
import { Redirect } from "react-router-dom";

function Header({ appName, user, onAuth, onLogout }) {
    function renderUserData() {
        return (
            <div className='navbar navbar-expand navbar-dark bg-dark'>
                <div>
                    <img
                        width='35'
                        className='avatar circle responsive-img'
                        src={"https://e7.pngegg.com/pngimages/778/849/png-clipart-computer-icons-user-login-avatar-small-icons-angle-heroes-thumbnail.png"}
                    />
                </div>

                <div className='navbar-brand'>{user.email}</div>
                <div className='navbar-brand'></div>

                <div>
                    <button>
                        <Redirect to="/contactos" />
                        <img src="https://cdn4.iconfinder.com/data/icons/simple-soft/512/open_door-256.png"
                            width="50" height="100" alt="Logo"
                            onClick={onLogout}
                            href="/contactos"
                        />
                    </button>
                </div>
            </div>
        )
    }

    function renderLoginButton() {
        return (
            {/* <button
                className='navbar-brand'
                onClick={onAuth}
            >
                Iniciar Sesión
            </button> */},
            <a href="/login" className="navbar-brand">
                Iniciar Sesión
            </a>
        )
    }

    return (
        <nav className='navbar navbar-expand navbar-dark bg-dark'>
            <a href="/contactos" className="navbar-brand">
                Contactos
            </a>

            <div className='nav-wrapper container'>
                <a href='/contactos' className='left brand-logo'>{appName}</a>
                {user ? renderUserData() : renderLoginButton()}
            </div>
        </nav>
    )
}

export default Header