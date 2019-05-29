import React from 'react';

export default props => {
    return (
        <select value={props.value} onChange={e => props.onChange(e.target.value)} >
            {props.values.sort().map(d =>
                <option key={d} value={d}>
                    {d}
                </option>
            )}
        </select>
    )
}
