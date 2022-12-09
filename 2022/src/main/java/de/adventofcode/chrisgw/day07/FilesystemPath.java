package de.adventofcode.chrisgw.day07;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


@Data
public class FilesystemPath {


    private final String path;
    private final FilesystemPath parent;
    private long size;
    private List<FilesystemPath> children;


    public FilesystemPath() {
        this(null, "/", 0);
        this.children = new ArrayList<>();
    }

    private FilesystemPath(FilesystemPath parentPath, String path, long size) {
        this.parent = parentPath;
        this.path = path;
        this.size = size;
    }


    public String fullPath() {
        if (parent == null) {
            return "";
        }
        return parent.fullPath() + "/" + path;
    }


    public boolean isRootPath() {
        return parent == null;
    }

    public boolean isFile() {
        return children == null;
    }

    public boolean isDirectory() {
        return !isFile();
    }


    public Stream<FilesystemPath> parents() {
        return Stream.iterate(parent, Objects::nonNull, FilesystemPath::getParent);
    }

    public Stream<FilesystemPath> allChildren() {
        if (isFile()) {
            return Stream.of(this);
        }
        return Stream.concat(Stream.of(this), children.stream().flatMap(FilesystemPath::allChildren));
    }


    public FilesystemPath findDirectory(String changePathString) {
        return children.stream()
                .filter(filesystemPath -> filesystemPath.path.equals(changePathString))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("could not find directory: " + changePathString));
    }


    public FilesystemPath addFile(String filename, long size) {
        var file = new FilesystemPath(this, filename, size);
        children.add(file);
        this.size += size;
        parents().forEach(filesystemPath -> filesystemPath.size += size);
        return file;
    }

    public FilesystemPath addDirectory(String name) {
        var directory = new FilesystemPath(this, name, 0);
        directory.children = new ArrayList<>();
        children.add(directory);
        return directory;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FilesystemPath that)) {
            return false;
        }
        return new EqualsBuilder().append(getSize(), that.getSize())
                .append(getPath(), that.getPath())
                .append(getParent(), that.getParent())
                .append(getChildren(), that.getChildren())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getPath()).append(getParent()).append(getSize()).toHashCode();
    }

    @Override
    public String toString() {
        if (isDirectory()) {
            return fullPath() + " (dir, " + size + ")";
        }
        return fullPath() + " (file, " + size + ")";
    }

}
