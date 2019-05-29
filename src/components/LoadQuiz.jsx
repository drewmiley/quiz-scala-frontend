import React, { useEffect, useState } from 'react';
import AutoComplete from 'react-autocomplete';

export default props => {
    const [value, setValue] = useState('');

    const onClick = () => props.loadQuiz(value);

    return (
        <div id="quiz-loader">
            <h4>LOAD QUIZ</h4>
            <AutoComplete
                getItemValue={item => item}
                items={props.validQuizCodes}
                renderItem={(item, isHighlighted) =><div style={{ background: isHighlighted ? 'lightgray' : 'white' }} key={item}>{item}</div>}
                shouldItemRender={(item, value) => item.includes(value)}
                value={value}
                onChange={e => setValue(e.target.value)}
                onSelect={val => setValue(val)}
            />
            <button id="load" onClick={onClick} disabled={!value}>Load</button>
        </div>
    );
}
