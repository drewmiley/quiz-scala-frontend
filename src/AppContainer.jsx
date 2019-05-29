import React, { Component } from 'react';
import {connect} from 'react-redux';
import { Router, Route, Link } from "react-router-dom";

import './app.css';

import history from './history';

import { mapDispatchToProps } from './ducks/actions';

import Init from './routes/Init';
import Leaderboards from './routes/Leaderboards';
import Quiz from './routes/Quiz';

class App extends Component {
    componentDidMount() {
        this.props.getValidQuizCodes();
        this.props.getValidQuizOptions();
    }
    render() {
        return <Router history={history}>
            <>
                <nav>
                    <ul>
                        <li><Link to="/">Init</Link></li>
                        <li><Link to="/quiz/">Quiz</Link></li>
                        <li><Link to="/leaderboards/">Leaderboards</Link></li>
                    </ul>
                </nav>

                <Route
                    path="/"
                    exact
                    render={(routeProps) => (
                        <Init {...this.props} />
                    )}
                />
                <Route
                    path="/quiz/"
                    exact
                    render={(routeProps) => (
                        <Quiz {...this.props} />
                    )}
                />
                <Route
                    path="/leaderboards/"
                    exact
                    render={(routeProps) => (
                        <Leaderboards {...this.props} />
                    )}
                />
            </>
        </Router>
    }
}

export const AppContainer = connect(state => state, mapDispatchToProps)(App);
