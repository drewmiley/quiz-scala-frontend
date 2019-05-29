import React from 'react';
import ReactDOM from 'react-dom';
import {PersistGate} from 'redux-persist/integration/react';
import {Provider} from 'react-redux';

import {AppContainer} from './AppContainer';

import initStore from './ducks/store';

const { store, persistor } = initStore();

ReactDOM.render(
    <Provider store={store}>
        <PersistGate loading={<h1>Loading</h1>} persistor={persistor}>
            <AppContainer />
        </PersistGate>
    </Provider>,
    document.getElementById('app')
);
