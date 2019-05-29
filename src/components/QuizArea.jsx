import React, { useEffect, useState } from 'react';

import Question from './Question';

export default props => {
    const mappedQuestions = quiz => quiz
        .map(d => ({
            question: d.question,
            answers: [d.answer].concat(d.incorrectAnswers).sort(() => 0.5 - Math.random())
        }));

    const [questions, setQuestions] = useState(mappedQuestions(props.quiz));

    useEffect(() => setQuestions(mappedQuestions(props.quiz)), [props.quiz]);

    return (
        <div id="quiz-area">
            {props.code.length > 0 &&
                <h4 id="quiz-code">Code: {props.code}</h4>
            }
            {questions.map((d, i) =>
                <Question
                    key={i}
                    setAnswer={props.setAnswer}
                    question={d.question}
                    selectedAnswer={props.answers.find(qa => qa.question == d.question)}
                    answers={d.answers}
                />
            )}
        </div>
    );
}
