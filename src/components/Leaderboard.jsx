import React from 'react';

export default props => {
    return (
        <div id="leaderboard">
            <h2>{props.code} LEADERBOARD</h2>
            {props.leaderboard.sort((a, b) => b.score - a.score)
                .reduce((acc, d) => {
                    const position = acc.length && d.score == acc[acc.length - 1].score ?
                        acc[acc.length - 1].position :
                        acc.length + 1;
                    return acc.concat([{ position, user: d.user, score: d.score }]);
                }, [])
                .map(d =>
                    <div key={d.user}>{d.position} - {d.user} - {d.score}</div>
                )
            }
        </div>
    );
}
