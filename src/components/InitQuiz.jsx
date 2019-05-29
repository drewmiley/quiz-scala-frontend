import React from 'react';
import { withRouter } from "react-router-dom";

import GenerateQuiz from './GenerateQuiz';
import LoadQuiz from './LoadQuiz';

export default withRouter(props => {
    return (
        <div id="quiz-init">
            <LoadQuiz
                loadQuiz={props.loadQuiz}
                validQuizCodes={props.validQuizCodes}
            />
            <GenerateQuiz
                generateQuiz={props.generateQuiz}
                validQuizOptions={props.validQuizOptions}
            />
        </div>
    );
})
