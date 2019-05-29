import React from 'react';

import InitQuiz from '../components/InitQuiz';

export default props => {
    return <>
        <InitQuiz
            loadQuiz={props.loadQuiz}
            generateQuiz={props.generateQuiz}
            validQuizCodes={props.validQuizCodes}
            validQuizOptions={props.validQuizOptions}
        />
    </>
};
