package ge.edu.sangu.kanban.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     *
     * @param boardEditForm
     */
    @Transactional
    public void updateBoard(BoardEditForm boardEditForm) {
        Board board = getBoardById(boardEditForm.getId());
        board.setTitle(boardEditForm.getTitle());
        board.setDescription(boardEditForm.getDescription());
        boardRepository.save(board);
    }

    /**
     * Finds and returns Board entity using ID.
     *
     * @param id
     * @return Found board entity object
     * @throws IllegalArgumentException If board with that ID was not found
     */
    public Board getBoardById(Long id) throws IllegalArgumentException {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Board found for id: " + id));
    }
}
