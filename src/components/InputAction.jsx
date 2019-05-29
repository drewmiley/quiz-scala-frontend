import React, { useState } from 'react';

export default props => {
    const [value, setValue] = useState('');

    const onChange = e => setValue(e.target.value);

    const onClick = () => props.action(value);

    return (
        <div id={props.id}>
            <input type="text" value={value} onChange={onChange} />
            <button onClick={onClick} disabled={props.disabled(value)}>{props.buttonText}</button>
        </div>
    );
}
