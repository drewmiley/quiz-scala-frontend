import React from 'react';

export default props => {
    return (
        <div id="quiz-question">
            <h3>{props.question}</h3>
            <>
                {props.answers.map((answer, i) =>
                    <button
                        key={i}
                        onClick={() => props.setAnswer(props.question, answer)}
                        style={props.selectedAnswer ? { backgroundColor: answer == props.selectedAnswer.answer ? 'lightblue': 'white', display: 'list-item' } : { display: 'list-item' }}
                    >
                        {answer}
                    </button>
                )}
            </>
        </div>
    );
}
