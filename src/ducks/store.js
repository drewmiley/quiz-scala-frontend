import { createStore, applyMiddleware } from 'redux';
import { persistStore, persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage';
import thunk from 'redux-thunk';

import reducer from './reducer';

const persistConfig = { key: 'root', storage };
const persistedReducer = persistReducer(persistConfig, reducer);

const initialState = {
    code: '',
    quiz: [],
    leaderboard: [],
    leaderboards: [],
    answers: [],
    validQuizCodes: [],
    validQuizOptions: { category: [], difficulty: [], type: [] }
}

export default () => {
    const store = createStore(persistedReducer, initialState, applyMiddleware(thunk));
    const persistor = persistStore(store);
    return { store, persistor };
}
