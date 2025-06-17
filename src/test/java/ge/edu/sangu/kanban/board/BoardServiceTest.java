package ge.edu.sangu.kanban.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    @Test
    void testGetBoardById() {
        Board board = new Board(1L, "Fake Board", "Fake Description", List.of());
        when(boardRepository.findById(1L)).thenReturn(
                Optional.of(board)
        );
        assertThat(boardService.getBoardById(1L)).isEqualTo(board);
        verify(boardRepository).findById(1L);
    }

    @Test
    void TestGetBoardByIdFailure() {
        when(boardRepository.findById(10L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> boardService.getBoardById(10L)).isInstanceOf(IllegalArgumentException.class);
        verify(boardRepository).findById(10L);
    }
}
