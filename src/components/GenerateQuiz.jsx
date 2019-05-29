import React, { useState } from 'react';

import Select from './Select';

export default props => {
    const [amount, setAmount] = useState('');
    const [category, setCategory] = useState('General Knowledge');
    const [difficulty, setDifficulty] = useState('any');
    const [type, setType] = useState('any');

    const onClick = () => props.generateQuiz({ amount, category, difficulty, type});

    return (
        <div id="quiz-generator">
            <h4>GENERATE QUIZ</h4>
            <div id="options">
                <input type="number" id="amount" min="1" placeholder="Amount" value={amount} onChange={e => setAmount(e.target.value)} />
                <Select
                    value={category}
                    values={props.validQuizOptions.category.sort()}
                    onChange={setCategory}
                />
                <Select
                    value={difficulty}
                    values={props.validQuizOptions.difficulty}
                    onChange={setDifficulty}
                />
                <Select
                    value={type}
                    values={props.validQuizOptions.type}
                    onChange={setType}
                />
            </div>
            <button id="generate" onClick={onClick} disabled={!amount}>Generate</button>
        </div>
    );
}
