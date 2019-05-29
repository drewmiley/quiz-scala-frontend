import * as actiontypes from './actiontypes';

import history from '../history';

const endpoint = process.env.NODE_ENV == 'production' ?
    'https://quiz-node-backend.herokuapp.com' :
    'http://localhost:8080';

export const mapDispatchToProps = (dispatch, getState) => ({
    loadQuiz: code => dispatch(loadQuiz(code)),
    generateQuiz: options => dispatch(generateQuiz(options)),
    setAnswer: (question, answer) => dispatch(setAnswer(question, answer)),
    submitAnswers: (code, user, answers) => dispatch(submitAnswers(code, user, answers)),
    getLeaderboards: user => dispatch(getLeaderboards(user)),
    getValidQuizCodes: () => dispatch(getValidQuizCodes()),
    getValidQuizOptions: () => dispatch(getValidQuizOptions())
});

const loadQuiz = code => async dispatch => {
    const quizResponse = await fetch(`${ endpoint }/api/quiz/${ code }`);
    const quiz = await quizResponse.json();
    const leaderboardResponse = await fetch(`${ endpoint }/api/leaderboard/${ code }`);
    const leaderboard = await leaderboardResponse.json();
    const loadQuizAction = (quiz, code, leaderboard) => {
        return {
            type: actiontypes.LOAD_QUIZ,
            payload: {
                quiz,
                code,
                leaderboard
            }
        }
    }
    dispatch(loadQuizAction(quiz.quiz, code, leaderboard.results));
    history.push('/quiz/');
};

const generateQuiz = options => async dispatch => {
    const quizResponse = await fetch(`${ endpoint }/api/newquiz`,
        { method: "POST", body: JSON.stringify({ options }), headers: { "Accept": "application/json", "Content-Type": "application/json" }});
    const quiz = await quizResponse.json();
    const validQuizCodesResponse = await fetch(`${ endpoint }/api/quizcodes`);
    const validQuizCodes = await validQuizCodesResponse.json();
    const generateQuizAction = (quiz, code) => {
        return {
            type: actiontypes.GENERATE_QUIZ,
            payload: {
                quiz,
                code,
                validQuizCodes
            }
        }
    }
    dispatch(generateQuizAction(quiz.quiz, quiz.code, validQuizCodes));
    history.push('/quiz/');
};

const setAnswer = (question, answer) => dispatch => {
    const setAnswerAction = (question, answer) => {
        return {
            type: actiontypes.SET_ANSWER,
            payload: {
                question,
                answer
            }
        }
    }
    dispatch(setAnswerAction(question, answer));
};

const submitAnswers = (user) => async (dispatch, getState) => {
    const state = getState();
    const submitAnswersResponse = await fetch(`${ endpoint }/api/answers/${ state.code }/${ user }`,
        { method: "POST", body: JSON.stringify({ answers: state.answers }), headers: { "Accept": "application/json", "Content-Type": "application/json" }});
    const submitAnswers = await submitAnswersResponse.json();
    const leaderboardResponse = await fetch(`${ endpoint }/api/leaderboard/${ state.code }`);
    const leaderboard = await leaderboardResponse.json();
    const submitAnswersAction = leaderboard => {
        return {
            type: actiontypes.SUBMIT_ANSWERS,
            payload: {
                leaderboard
            }
        }
    }
    dispatch(submitAnswersAction(leaderboard.results));
};

const getLeaderboards = user => async dispatch => {
    const leaderboardsResponse = await fetch(`${ endpoint }/api/leaderboards${ user ? `?user=${ user }` : '' }`);
    const leaderboards = await leaderboardsResponse.json();
    const getLeaderboardsAction = leaderboards => {
        return {
            type: actiontypes.GET_LEADERBOARDS,
            payload: {
                leaderboards
            }
        }
    }
    dispatch(getLeaderboardsAction(leaderboards));
}

const getValidQuizCodes = user => async dispatch => {
    const validQuizCodesResponse = await fetch(`${ endpoint }/api/quizcodes`);
    const validQuizCodes = await validQuizCodesResponse.json();
    const getValidQuizCodesAction = validQuizCodes => {
        return {
            type: actiontypes.GET_VALIDQUIZCODES,
            payload: {
                validQuizCodes
            }
        }
    }
    dispatch(getValidQuizCodesAction(validQuizCodes));
}

const getValidQuizOptions = user => async dispatch => {
    const validQuizOptionsResponse = await fetch(`${ endpoint }/api/quizoptions`);
    const validQuizOptions = await validQuizOptionsResponse.json();
    const getValidQuizOptionsAction = validQuizOptions => {
        return {
            type: actiontypes.GET_VALIDQUIZOPTIONS,
            payload: {
                validQuizOptions
            }
        }
    }
    dispatch(getValidQuizOptionsAction(validQuizOptions));
}
